package com.sottti.android.app.template.data.items.datasource.local.mapper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

internal class RoomPagingSourceFake(
    private val loader: suspend (LoadParams<Int>) -> LoadResult<Int, ItemRoomModel>,
) : PagingSource<Int, ItemRoomModel>() {
    override fun getRefreshKey(state: PagingState<Int, ItemRoomModel>): Int? = null
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemRoomModel> {
        if (invalid) return LoadResult.Invalid()
        return loader(params)
    }
}
