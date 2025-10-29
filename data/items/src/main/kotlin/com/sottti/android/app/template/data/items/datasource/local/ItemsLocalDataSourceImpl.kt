package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.database.RemoteKeysDao
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.model.Item
import javax.inject.Inject

internal class ItemsLocalDataSourceImpl @Inject constructor(
    private val itemsDao: ItemsDao,
    private val remoteKeysDao: RemoteKeysDao,
) : ItemsLocalDataSource {

    override fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSource(roomPagingSource = itemsDao.observeItems())

    override suspend fun upsert(items: List<Item>) {
        itemsDao.upsert(items = items.toRoom())
    }

    override suspend fun clearAndInsert(
        items: List<Item>,
    ) {
        itemsDao.clear()
        itemsDao.upsert(items = items.toRoom())
    }

    override suspend fun clearAll() {
        itemsDao.clear()
        remoteKeysDao.clear()
    }

    override suspend fun getNextRemotePage() =
        remoteKeysDao.get()

    override suspend fun runTransaction(
        block: suspend () -> Unit,
    ) {
        remoteKeysDao.withTransaction(block)
    }

    override suspend fun updateRemoteKeys(
        nextPage: Int?,
        prevPage: Int?,
    ) {
        remoteKeysDao.upsert(
            RemoteKeysRoomModel(
                anchor = "items",
                nextPage = nextPage,
                prevPage = prevPage,
            )
        )
    }


}
