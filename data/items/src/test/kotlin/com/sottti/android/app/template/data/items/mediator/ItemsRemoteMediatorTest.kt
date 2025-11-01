package com.sottti.android.app.template.data.items.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSourceFake
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSourceFake
import com.sottti.android.app.template.fixtures.listOfMultipleItems
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
internal class ItemsRemoteMediatorTest {

    private lateinit var localDataSource: ItemsLocalDataSourceFake
    private lateinit var remoteDataSource: ItemsRemoteDataSourceFake
    private lateinit var mediator: ItemsRemoteMediator

    private val pagingState = PagingState<Int, Item>(
        pages = emptyList(),
        anchorPosition = null,
        config = PagingConfig(pageSize = 10),
        leadingPlaceholderCount = 0,
    )

    @Before
    fun setUp() {
        localDataSource = ItemsLocalDataSourceFake()
        remoteDataSource = ItemsRemoteDataSourceFake()
        mediator = ItemsRemoteMediator(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `load with refresh type returns success`() = runTest {
        remoteDataSource.setSuccessResponse(emptyList())

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(Success::class.java)
    }

    @Test
    fun `load with refresh type clears local data and inserts new data`() = runTest {
        localDataSource.upsert(listOfMultipleItems)
        remoteDataSource.setSuccessResponse(listOfMultipleItems)

        mediator.load(LoadType.REFRESH, pagingState)

        assertThat(localDataSource.clearAllCalled).isTrue()
        assertThat(localDataSource.clearAndInsertCalled).isTrue()
        assertThat(localDataSource.saved.size).isEqualTo(listOfMultipleItems.size)
    }

    @Test
    fun `load with refresh and empty remote data returns end of pagination`() = runTest {
        remoteDataSource.setSuccessResponse(emptyList())

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertThat((result as Success).endOfPaginationReached).isTrue()
    }

    @Test
    fun `load with APPEND and no next page returns end of pagination`() = runTest {
        localDataSource.remoteKeys =
            RemoteKeysRoomModel(
                anchor = "items",
                nextPage = null,
                prevPage = null,
            )

        val result = mediator.load(LoadType.APPEND, pagingState)

        assertThat(result).isInstanceOf(Success::class.java)
        assertThat((result as Success).endOfPaginationReached).isTrue()
    }

    @Test
    fun `load with append and next page available fetches and upserts data`() = runTest {
        localDataSource.remoteKeys =
            RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleItems.take(5))

        val result = mediator.load(LoadType.APPEND, pagingState)

        assertThat(result).isInstanceOf(Success::class.java)
        assertThat(remoteDataSource.lastCalledPageNumber?.value).isEqualTo(3)
        assertThat(localDataSource.upsertCalled).isTrue()
        assertThat(localDataSource.saved.size).isEqualTo(5)
    }

    @Test
    fun `load with prepend returns end of pagination`() = runTest {
        val result = mediator.load(LoadType.PREPEND, pagingState)
        assertThat(result).isInstanceOf(Success::class.java)
        assertThat((result as Success).endOfPaginationReached).isTrue()
    }

    @Test
    fun `load on remote error returns error result`() = runTest {
        val exception = kotlinx.io.IOException("Network Failure")
        remoteDataSource.setErrorResponse(exception)

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).throwable).isEqualTo(exception)
    }

    @Test
    fun `refresh forwards page 1 and updates remote keys`() = runTest {
        remoteDataSource.setSuccessResponse(listOfMultipleItems.take(10))

        val result = mediator.load(LoadType.REFRESH, pagingState) as Success
        assertThat(result.endOfPaginationReached).isFalse()

        assertThat(remoteDataSource.lastCalledPageNumber?.value).isEqualTo(1)
        assertThat(remoteDataSource.lastCalledPageSize?.value).isEqualTo(10)

        assertThat(localDataSource.remoteKeys?.prevPage).isNull()
        assertThat(localDataSource.remoteKeys?.nextPage).isEqualTo(2)
    }

    @Test
    fun `refresh empty sets remote keys to nulls`() = runTest {
        remoteDataSource.setSuccessResponse(emptyList())

        val result = mediator.load(LoadType.REFRESH, pagingState) as Success
        assertThat(result.endOfPaginationReached).isTrue()
        assertThat(localDataSource.remoteKeys?.prevPage).isNull()
        assertThat(localDataSource.remoteKeys?.nextPage).isNull()
    }

    @Test
    fun `append updates remote keys to next page`() = runTest {
        localDataSource.remoteKeys =
            RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleItems.take(10))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isFalse()
        assertThat(remoteDataSource.lastCalledPageNumber?.value).isEqualTo(3)
        assertThat(localDataSource.remoteKeys?.nextPage).isEqualTo(4)
        assertThat(localDataSource.remoteKeys?.prevPage).isEqualTo(2)
    }

    @Test
    fun `append with short page marks end and clears next`() = runTest {
        localDataSource.remoteKeys =
            RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleItems.take(5))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isTrue()
        assertThat(localDataSource.remoteKeys?.nextPage).isNull()
        assertThat(localDataSource.remoteKeys?.prevPage).isEqualTo(2)
    }

    @Test
    fun `append with no remote keys ends pagination without network call`() = runTest {
        localDataSource.remoteKeys = null
        remoteDataSource.setSuccessResponse(listOfMultipleItems.take(10))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isTrue()
        assertThat(remoteDataSource.lastCalledPageNumber).isNull()
    }
}
