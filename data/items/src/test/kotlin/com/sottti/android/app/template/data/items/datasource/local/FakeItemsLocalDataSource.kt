package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.fakes.FakeItemMappingPagingSource
import com.sottti.android.app.template.model.Item

internal class FakeItemsLocalDataSource : ItemsLocalDataSource {

    private val activeSources = mutableSetOf<FakeItemMappingPagingSource>()
    val saved: MutableList<Item> = mutableListOf()
    var clearCalls = 0

    override fun observeItems(): PagingSource<Int, Item> =
        FakeItemMappingPagingSource(saved)
            .also { source -> activeSources += source }

    override suspend fun saveItems(items: List<Item>) {
        saved.addAll(items)
        activeSources
            .forEach { pagingSource -> pagingSource.invalidate() }
    }

    override suspend fun clearAll() {
        clearCalls++
        saved.clear()
        activeSources
            .forEach { pagingSource -> pagingSource.invalidate() }
    }
}
