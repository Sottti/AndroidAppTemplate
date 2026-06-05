package com.sottti.android.app.template.data.characters.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.github.michaelbull.result.getOrThrow
import com.sottti.android.app.template.data.characters.datasource.local.CharactersLocalDataSource
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersRemoteDataSource
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.domain.characters.model.Character
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalPagingApi::class)
internal class CharactersRemoteMediator @Inject constructor(
    val localDataSource: CharactersLocalDataSource,
    val remoteDataSource: CharactersRemoteDataSource,
) : RemoteMediator<Int, Character>() {

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>,
    ): MediatorResult {
        return try {
            val pageSize = state.config.pageSize

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> null
                LoadType.APPEND -> {
                    val keys = localDataSource.getNextRemotePage()
                    keys?.nextPage
                }
            }

            if (page == null) {
                return Success(endOfPaginationReached = true)
            }

            val characters = remoteDataSource.getCharacters(
                pageNumber = PageNumberApiModel(page),
            ).getOrThrow()

            val endReached = characters.size < pageSize || characters.isEmpty()

            localDataSource.runTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearAll()
                }

                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearAndInsert(characters)
                    val next = if (endReached) null else 2
                    localDataSource.updateRemoteKeys(nextPage = next, prevPage = null)
                } else {
                    localDataSource.upsert(characters)
                    val next = if (endReached) null else page + 1
                    val prev = if (page == 1) null else page - 1
                    localDataSource.updateRemoteKeys(nextPage = next, prevPage = prev)
                }
            }

            Success(endOfPaginationReached = endReached)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
