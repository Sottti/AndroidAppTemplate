package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.model.Item
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

internal class ItemsLocalDataSourceImpl @Inject constructor(
    private val itemsDao: ItemsDao,
    private val timeProvider: TimeProvider,
) : ItemsLocalDataSource {

    private val expirationTime = 30.minutes.inWholeMilliseconds

    override fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSource(roomPagingSource = itemsDao.observeItems())

    override suspend fun needRefresh(): Boolean {
        val oldestTimestamp = itemsDao.getOldestItemTimestamp() ?: return true
        val isExpired = (timeProvider.nowInMillis() - oldestTimestamp) > expirationTime
        return isExpired
    }

    override suspend fun upsert(
        clearExisting: Boolean,
        items: List<Item>,
    ) {
        if (clearExisting) {
            itemsDao.clearAll()
        }
        itemsDao.upsert(items = items.toRoom(timeProvider.nowInMillis()))
    }
}
