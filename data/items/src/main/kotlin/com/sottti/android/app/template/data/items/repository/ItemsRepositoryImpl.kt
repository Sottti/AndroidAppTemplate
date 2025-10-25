package com.sottti.android.app.template.data.items.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.local.paging.ItemsRemoteMediator
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ItemsRepositoryImpl @Inject constructor(
    private val localDataSource: ItemsLocalDataSource,
    private val remoteMediator: ItemsRemoteMediator,
) : ItemsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun observeItems(): Flow<PagingData<Item>> =
        Pager(
            config = PagingConfig(
                enablePlaceholders = true,
                initialLoadSize = 25,
                pageSize = 25,
                prefetchDistance = 25,
            ),
            pagingSourceFactory = { localDataSource.observeItems() },
            remoteMediator = remoteMediator,
        ).flow
}
