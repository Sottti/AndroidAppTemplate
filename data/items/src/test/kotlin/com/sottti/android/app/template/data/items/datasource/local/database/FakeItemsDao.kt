package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.FakeItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class FakeItemsDao : ItemsDao {
    val saved = mutableListOf<ItemRoomModel>()
    var clearCalled = false
    var upsertCalled = false

    private val itemsFlow = MutableStateFlow<List<ItemRoomModel>>(emptyList())

    override fun observeItems(): PagingSource<Int, ItemRoomModel> {
        return FakeItemMappingPagingSource(saved.map { it.toDomain() }.toMutableList())
            .let { it as PagingSource<Int, ItemRoomModel> }
    }

    override suspend fun upsert(items: List<ItemRoomModel>) {
        upsertCalled = true
        items.forEach { newItem ->
            val index = saved.indexOfFirst { it.id == newItem.id }
            when {
                index != -1 -> saved[index] = newItem
                else -> saved.add(newItem)
            }
        }
        itemsFlow.value = saved.toList()
    }

    override suspend fun upsert(item: ItemRoomModel) {
        upsertCalled = true
        val index = saved.indexOfFirst { it.id == item.id }
        if (index != -1) saved[index] = item else saved.add(item)
        itemsFlow.value = saved.toList()
    }

    override fun getItem(itemId: Int): ItemRoomModel {
        return saved.first { it.id == itemId }
    }

    override fun observeItem(itemId: Int): Flow<ItemRoomModel> {
        return itemsFlow
            .map { list -> list.first { it.id == itemId } }
            .distinctUntilChanged()
    }

    override suspend fun clear() {
        clearCalled = true
        saved.clear()
        itemsFlow.value = emptyList()
    }
}
