package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.fakes.FakeRoomPagingSource
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

internal class FakeItemsDao(
    private val pagingSource: FakeRoomPagingSource = FakeRoomPagingSource {
        PagingSource.LoadResult.Page(emptyList(), null, null)
    },
) : ItemsDao {
    val insertedItems = mutableListOf<ItemRoomModel>()
    var clearCalls = 0

    override suspend fun insertOrUpdate(items: List<ItemRoomModel>) {
        insertedItems.addAll(items)
    }

    override fun observeItems(): PagingSource<Int, ItemRoomModel> =
        pagingSource

    override suspend fun clearAll() {
        clearCalls++
    }
}
