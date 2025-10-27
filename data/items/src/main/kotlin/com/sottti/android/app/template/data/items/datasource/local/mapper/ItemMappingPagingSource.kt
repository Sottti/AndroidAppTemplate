package com.sottti.android.app.template.data.items.datasource.local.mapper

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Invalid
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import com.sottti.android.app.template.model.Item

internal class ItemMappingPagingSource(
    private val roomPagingSource: PagingSource<Int, ItemRoomModel>,
) : PagingSource<Int, Item>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, Item> =
        when (val roomResult = roomPagingSource.load(params)) {
            is Invalid -> Invalid()
            is Error -> Error(roomResult.throwable)
            is Page -> Page(
                data = roomResult.data.map { it.toDomain() },
                prevKey = roomResult.prevKey,
                nextKey = roomResult.nextKey,
                itemsBefore = roomResult.itemsBefore,
                itemsAfter = roomResult.itemsAfter,
            )
        }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }


    init {
        roomPagingSource.registerInvalidatedCallback(::invalidate)
    }
}
