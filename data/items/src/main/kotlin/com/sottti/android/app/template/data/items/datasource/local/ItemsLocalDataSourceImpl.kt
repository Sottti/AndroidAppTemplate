package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.model.Item
import javax.inject.Inject

internal class ItemsLocalDataSourceImpl @Inject constructor(
    private val dao: ItemsDao,
) : ItemsLocalDataSource {

    override fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSource(roomPagingSource = dao.observeItems())

    override suspend fun insertOrUpdate(
        clearExisting: Boolean,
        items: List<Item>,
    ) {
        dao.clearAndInsertOrUpdate(
            clearExisting = clearExisting,
            items = items.toRoom(),
        )
    }
}
