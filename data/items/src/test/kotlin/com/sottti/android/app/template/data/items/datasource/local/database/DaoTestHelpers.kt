package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

internal suspend fun PagingSource<Int, ItemRoomModel>.loadAsPage(
    loadParams: PagingSource.LoadParams.Refresh<Int>,
) = load(loadParams) as PagingSource.LoadResult.Page

internal val loadParamsRefresh: PagingSource.LoadParams.Refresh<Int> =
    PagingSource.LoadParams.Refresh(
        key = null,
        loadSize = 10,
        placeholdersEnabled = false
    )

internal fun Context.createDb() = Room
    .inMemoryDatabaseBuilder(this, ItemsDatabase::class.java)
    .allowMainThreadQueries()
    .build()
