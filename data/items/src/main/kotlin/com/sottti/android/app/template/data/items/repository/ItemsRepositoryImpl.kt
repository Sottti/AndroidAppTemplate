package com.sottti.android.app.template.data.items.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.mediator.ItemsRemoteMediator
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ItemsRepositoryImpl @Inject constructor(
    private val localDataSource: ItemsLocalDataSource,
    private val remoteMediator: ItemsRemoteMediator,
) : ItemsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun observeItems(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                prefetchDistance = 10,
                initialLoadSize = 50,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = { localDataSource.observeItems() },
            remoteMediator = remoteMediator,
        ).flow
    }
}
