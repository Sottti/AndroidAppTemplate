package com.sottti.android.app.template.data.items.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.michaelbull.result.onSuccess
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ItemsRepositoryImpl @Inject constructor(
    private val localDataSource: ItemsLocalDataSource,
    private val remoteDataSource: ItemsRemoteDataSource,
) : ItemsRepository {

    override fun observeItems(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 25,
                initialLoadSize = 75,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { localDataSource.observeItems() },
        ).flow
    }

    override suspend fun refreshItemsIfNeeded() {
        if (localDataSource.needRefresh()) {
            remoteDataSource
                .getItems()
                .onSuccess { items -> localDataSource.upsert(clearExisting = true, items = items) }
        }
    }
}
