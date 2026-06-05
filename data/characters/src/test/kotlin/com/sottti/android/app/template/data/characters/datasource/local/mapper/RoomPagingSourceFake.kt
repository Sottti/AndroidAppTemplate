package com.sottti.android.app.template.data.characters.datasource.local.mapper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel

internal class RoomPagingSourceFake(
    private val loader: suspend (LoadParams<Int>) -> LoadResult<Int, CharacterRoomModel>,
) : PagingSource<Int, CharacterRoomModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterRoomModel>): Int? = null
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterRoomModel> {
        if (invalid) return LoadResult.Invalid()
        return loader(params)
    }
}
