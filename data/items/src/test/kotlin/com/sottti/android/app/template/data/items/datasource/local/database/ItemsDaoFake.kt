package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.RoomPagingSourceFake
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class ItemsDaoFake : ItemsDao {
    val saved = mutableListOf<ItemRoomModel>()
    var clearCalled = false
    var upsertCalled = false

    private val itemsFlow = MutableStateFlow<List<ItemRoomModel>>(emptyList())

    override fun observeItems(): PagingSource<Int, ItemRoomModel> {
        return RoomPagingSourceFake { params ->
            val snapshot = saved.toList()
            val start = (params.key ?: 0).coerceAtLeast(0)
            val end = (start + params.loadSize).coerceAtMost(snapshot.size)

            PagingSource.LoadResult.Page(
                data = snapshot.subList(start, end),
                prevKey = if (start == 0) null else maxOf(0, start - params.loadSize),
                nextKey = if (end < snapshot.size) end else null,
                itemsBefore = start,
                itemsAfter = snapshot.size - end,
            )
        }
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

    override suspend fun getItem(itemId: Int): ItemRoomModel? {
        return saved.firstOrNull { it.id == itemId }
    }

    override fun observeItem(itemId: Int): Flow<ItemRoomModel?> {
        return itemsFlow
            .map { list -> list.firstOrNull { it.id == itemId } }
            .distinctUntilChanged()
    }

    override suspend fun clear() {
        clearCalled = true
        saved.clear()
        itemsFlow.value = emptyList()
    }
}
