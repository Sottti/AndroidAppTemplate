package com.sottti.android.app.template.data.items.datasource.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.github.michaelbull.result.mapBoth
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
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

            remoteDataSource.getItems(
                page = PageApiModel(page),
                pageSize = PageSizeApiModel(state.config.pageSize),
            ).mapBoth(
                success = { result ->
                    if (loadType == LoadType.REFRESH) localDataSource.clearAll()
                    if (result.items.isNotEmpty()) localDataSource.saveItems(result.items)
                    return Success(endOfPaginationReached = result.items.isEmpty())
                },
                failure = { exception -> return Error(exception) },
            )
        } catch (e: IOException) {
            Error(e)
        } catch (e: Exception) {
            Error(e)
        }
    }
}
