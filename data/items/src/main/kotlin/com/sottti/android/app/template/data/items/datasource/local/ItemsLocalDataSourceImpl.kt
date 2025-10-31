package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.database.RemoteKeysDao
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class ItemsLocalDataSourceImpl @Inject constructor(
    private val itemsDao: ItemsDao,
    private val remoteKeysDao: RemoteKeysDao,
    private val timeProvider: TimeProvider,
) : ItemsLocalDataSource {
    override fun observeItem(itemId: ItemId): Flow<Item?> =
        itemsDao
            .observeItem(itemId.value)
            .map { itemRoomModel -> itemRoomModel.toDomain() }

    override fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSource(roomPagingSource = itemsDao.observeItems())

    override suspend fun isExpired(itemId: ItemId): Boolean =
        itemsDao
            .getItem(itemId.value)
            .isExpired(timeProvider.now())

    override suspend fun upsert(item: Item) {
        itemsDao.upsert(item.toRoom(timeProvider.now()))
    }

    override suspend fun upsert(items: List<Item>) {
        itemsDao.upsert(items = items.toRoom(timeProvider.now()))
    }

    override suspend fun clearAndInsert(
        items: List<Item>,
    ) {
        itemsDao.clear()
        itemsDao.upsert(items = items.toRoom(timeProvider.now()))
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
