package com.sottti.android.app.template.data.items.datasource.local.paging

import android.util.Log.e
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.model.Item
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class ItemsRemoteMediator @Inject constructor(
    private val localDataSource: ItemsLocalDataSource,
    private val remoteDataSource: ItemsRemoteDataSource,
) : RemoteMediator<Int, Item>() {

    override suspend fun initialize(): InitializeAction =
        InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Item>,
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull() ?: return Success(endOfPaginationReached = false)
                    val totalItemsLoaded = state.pages.sumOf { items -> items.data.size }
                    val numPagesLoaded = totalItemsLoaded / state.config.pageSize
                    val nextPageToLoad = numPagesLoaded + 1
                    nextPageToLoad
                }
            }

            val networkItems = remoteDataSource.getItems(
                page = page,
                pageSize = state.config.pageSize,
            )
            if (networkItems.isNotEmpty()) {
                localDataSource.saveItems(networkItems)
            }
            Success(endOfPaginationReached = networkItems.isEmpty())
        } catch (e: IOException) {
            Error(e)
        } catch (e: Exception) {
            Error(e)
        }
    }
}
