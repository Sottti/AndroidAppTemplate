package com.sottti.android.app.template.data.items.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.michaelbull.result.onSuccess
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.mediator.ItemsRemoteMediator
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import com.sottti.android.app.template.domain.items.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class ItemsRepositoryImpl @Inject constructor(
    private val localDataSource: ItemsLocalDataSource,
    private val remoteDataSource: ItemsRemoteDataSource,
    private val remoteMediator: ItemsRemoteMediator,
) : ItemsRepository {
    override fun observeItem(itemId: ItemId): Flow<Item> =
        localDataSource
            .observeItem(itemId)
            .onEach { item ->
                if (item == null || localDataSource.isExpired(item.id)) {
                    remoteDataSource
                        .getItem(itemId)
                        .onSuccess { localDataSource.upsert(it) }
                }
            }.filterNotNull()

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
