package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.FakeItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.model.Item

internal class FakeItemsLocalDataSource : ItemsLocalDataSource {

    val saved: MutableList<Item> = mutableListOf()
    var remoteKeys: RemoteKeysRoomModel? = null

    var clearAllCalled = false
        private set
    var clearAndInsertCalled = false
        private set
    var upsertCalled = false
        private set

    override fun observeItems(): PagingSource<Int, Item> =
        FakeItemMappingPagingSource(saved)

    override suspend fun clearAll() {
        clearAllCalled = true
        saved.clear()
        remoteKeys = null
    }

    override suspend fun clearAndInsert(items: List<Item>) {
        clearAndInsertCalled = true
        saved.clear()
        saved.addAll(items)
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
    }
}
