package com.sottti.android.app.template.data.items.datasource.local.mapper.fakes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.android.app.template.model.Item

internal class FakeItemMappingPagingSource(
    private val saved: MutableList<Item>,
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        if (invalid) return LoadResult.Invalid()

        val snapshot = saved.toList()
        val start = (params.key ?: 0).coerceAtLeast(0)
        val end = (start + params.loadSize).coerceAtMost(snapshot.size)
        val data = snapshot.subList(start, end)

        return LoadResult.Page(
            data = data,
            prevKey = if (start == 0) null else maxOf(0, start - params.loadSize),
            nextKey = if (end < snapshot.size) end else null,
            itemsBefore = start,
            itemsAfter = snapshot.size - end
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor)
        return page?.prevKey?.plus(state.config.pageSize)
            ?: page?.nextKey?.minus(state.config.pageSize)
    }
}
