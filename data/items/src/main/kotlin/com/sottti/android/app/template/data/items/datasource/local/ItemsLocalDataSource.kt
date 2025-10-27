package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.mapper.ItemMappingPagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.model.Item
import javax.inject.Inject

internal class ItemsLocalDataSource @Inject constructor(
    private val dao: ItemsDao,
) {
    fun observeItems(): PagingSource<Int, Item> =
        ItemMappingPagingSource(roomPagingSource = dao.observeItems())

    suspend fun saveItems(items: List<Item>) {
        dao.insertOrUpdate(items.map { it.toRoom() })
    }

    fun clearAll() {
        dao.clearAll()
    }
}
