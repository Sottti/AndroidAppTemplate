package com.sottti.android.app.template.data.items.datasource.local.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.github.michaelbull.result.getOrThrow
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.model.Item
import io.ktor.client.plugins.ResponseException
import kotlinx.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
internal class ItemsRemoteMediator @Inject constructor(
    val localDataSource: ItemsLocalDataSource,
    val remoteDataSource: ItemsRemoteDataSource,
) : RemoteMediator<Int, Item>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Item>,
    ): MediatorResult {
        return try {
            val pageSize = state.config.pageSize
            var page: Int

            when (loadType) {
                LoadType.REFRESH -> page = 1
                LoadType.PREPEND -> return Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val keys = localDataSource.getNextRemotePage()
                    val next = keys?.nextPage ?: return Success(endOfPaginationReached = true)
                    page = next
                }
            }

            val items = remoteDataSource.getItems(
                pageNumber = PageNumberApiModel(page),
                pageSize = PageSizeApiModel(pageSize),
            ).getOrThrow()

            val endReached = items.size < pageSize || items.isEmpty()

            localDataSource.runTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearAll()
                }

                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearAndInsert(items)
                    val next = if (endReached) null else 2
                    localDataSource.updateRemoteKeys(nextPage = next, prevPage = null)
                } else {
                    localDataSource.upsert(items)
                    val next = if (endReached) null else page + 1
                    val prev = if (page == 1) null else page - 1
                    localDataSource.updateRemoteKeys(nextPage = next, prevPage = prev)
                }
            }

            Success(endOfPaginationReached = endReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: ResponseException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
