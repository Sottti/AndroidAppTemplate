package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.FakeItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

internal class FakeItemsDao : ItemsDao {
    val saved = mutableListOf<ItemRoomModel>()
    var clearCalled = false
    var upsertCalled = false

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
    }

    override suspend fun clear() {
        clearCalled = true
        saved.clear()
    }
}
