package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.fakes.FakeItemMappingPagingSource
import com.sottti.android.app.template.model.Item

internal class FakeItemsLocalDataSource : ItemsLocalDataSource {

    private val activeSources = mutableSetOf<FakeItemMappingPagingSource>()
    val saved: MutableList<Item> = mutableListOf()
    override fun observeItems(): PagingSource<Int, Item> =
        FakeItemMappingPagingSource(saved)
            .also { source -> activeSources += source }

    override suspend fun upsert(items: List<Item>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAndInsert(items: List<Item>) {
        TODO("Not yet implemented")
    }
}
