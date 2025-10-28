package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.mapper.fakes.FakeItemMappingPagingSource
import com.sottti.android.app.template.model.Item

internal class FakeItemsLocalDataSource : ItemsLocalDataSource {

    private val activeSources = mutableSetOf<FakeItemMappingPagingSource>()
    val saved: MutableList<Item> = mutableListOf()
    var clearCalls = 0

    var nowMs: Long = 0L
    var pageTtlMs: Long = 15 * 60 * 1000L
    private val pageLastSync = mutableMapOf<Int, Long>()
    private val pageItemCounts = mutableMapOf<Int, Int>()

    override fun observeItems(): PagingSource<Int, Item> =
        FakeItemMappingPagingSource(saved)
            .also { source -> activeSources += source }

    override suspend fun needRefresh(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(
        clearExisting: Boolean,
        items: List<Item>,
    ) {
        TODO("Not yet implemented")
    }
}
