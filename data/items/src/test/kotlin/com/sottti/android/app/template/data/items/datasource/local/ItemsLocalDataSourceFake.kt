package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSourceFake
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class ItemsLocalDataSourceFake : ItemsLocalDataSource {

    val saved: MutableList<Item> = mutableListOf()
    var remoteKeys: RemoteKeysRoomModel? = null

    var clearAllCalled = false
        private set
    var clearAndInsertCalled = false
        private set
    var upsertCalled = false
        private set

    private val itemsFlow = MutableStateFlow<List<Item>>(emptyList())
    var expiredIds: MutableSet<ItemId> = mutableSetOf()

    override fun observeItem(itemId: ItemId): Flow<Item?> =
        itemsFlow
            .map { list -> list.firstOrNull { it.id == itemId } }
            .distinctUntilChanged()

    override fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSourceFake(saved)

    override suspend fun isExpired(itemId: ItemId): Boolean = itemId in expiredIds

    override suspend fun clearAll() {
        clearAllCalled = true
        saved.clear()
        remoteKeys = null
        itemsFlow.value = saved.toList()
    }

    override suspend fun clearAndInsert(items: List<Item>) {
        clearAndInsertCalled = true
        saved.clear()
        saved.addAll(items)
        itemsFlow.value = saved.toList()
    }

    override suspend fun getNextRemotePage(): RemoteKeysRoomModel? {
        return remoteKeys
    }

    override suspend fun runTransaction(block: suspend () -> Unit) {
        block()
    }

    override suspend fun updateRemoteKeys(nextPage: Int?, prevPage: Int?) {
        remoteKeys = RemoteKeysRoomModel(
            anchor = "items",
            nextPage = nextPage,
            prevPage = prevPage,
        )
    }

    override suspend fun upsert(item: Item) {
        upsertCalled = true
        val index = saved.indexOfFirst { it.id == item.id }
        if (index != -1) {
            saved[index] = item
        } else {
            saved.add(item)
        }
        itemsFlow.value = saved.toList()
    }

    override suspend fun upsert(items: List<Item>) {
        upsertCalled = true
        items.forEach { newItem ->
            val index = saved.indexOfFirst { it.id == newItem.id }
            if (index != -1) {
                saved[index] = newItem
            } else {
                saved.add(newItem)
            }
        }
        itemsFlow.value = saved.toList()
    }
}
