package com.sottti.android.app.template.data.items.datasource.local.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.RemoteMediator.InitializeAction
import androidx.paging.RemoteMediator.MediatorResult
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.item
import com.sottti.android.app.template.data.items.datasource.data.item2
import com.sottti.android.app.template.data.items.datasource.local.FakeItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.model.PaginatedItems
import com.sottti.android.app.template.data.items.datasource.remote.FakeItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
internal class ItemsRemoteMediatorTest {

    private lateinit var itemsRemoteMediator: ItemsRemoteMediator
    private lateinit var localDataSource: ItemsLocalDataSource
    private lateinit var remoteDataSource: ItemsRemoteDataSource

    private val items = listOf(item, item2)

    @Before
    fun setUp() {
        localDataSource = FakeItemsLocalDataSource()
    }

    @Test
    fun `initialize returns launch initial refresh`() = runTest {
        remoteDataSource = FakeItemsRemoteDataSource { _, _ -> Err(IOException()) }
        itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)

        assertThat(itemsRemoteMediator.initialize())
            .isEqualTo(InitializeAction.LAUNCH_INITIAL_REFRESH)
    }

    @Test
    fun `load refresh with empty remote data returns endOfPagination`() =
        runTest {
            val paginatedItems = PaginatedItems(items = emptyList(), nextPage = 1)
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(paginatedItems)
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
            val pagingState = createPagingState(pages = 0, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.REFRESH, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            assertThat((result as MediatorResult.Success).endOfPaginationReached).isTrue()
            assertThat(remoteCalled).isTrue()
        }

    @Test
    fun `when load is refresh with data, clears and saves items and returns success with end of pagination false`() =
        runTest {
            val paginatedItems = PaginatedItems(items = items, nextPage = 1)
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(paginatedItems)
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
            val pagingState = createPagingState(pages = 0, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.REFRESH, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            val success = result as MediatorResult.Success
            assertThat(success.endOfPaginationReached).isFalse()

            val fakeLocal = localDataSource as FakeItemsLocalDataSource
            assertThat(fakeLocal.clearCalls).isEqualTo(1)
            assertThat(fakeLocal.saved).containsExactlyElementsIn(items)
            assertThat(remoteCalled).isTrue()
        }

    @Test
    fun `when load is append and remote is success, returns success and saves data without clearing`() =
        runTest {
            val paginatedItems = PaginatedItems(items = items, nextPage = 1)
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(paginatedItems)
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)

            // Simulate having one page already loaded to trigger a valid append
            val pagingState = createPagingState(pages = 1, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.APPEND, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            assertThat((result as MediatorResult.Success).endOfPaginationReached).isFalse()
            assertThat(remoteCalled).isTrue()
        }

    @Test
    fun `when load is append and remote data is empty, returns success with end of pagination true`() =
        runTest {
            val paginatedItems = PaginatedItems(items = emptyList(), nextPage = 1)
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(paginatedItems)
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
            val pagingState = createPagingState(pages = 1, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.APPEND, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            val success = result as MediatorResult.Success
            assertThat(success.endOfPaginationReached).isTrue()

            val fakeLocal = localDataSource as FakeItemsLocalDataSource
            assertThat(fakeLocal.clearCalls).isEqualTo(0)
            assertThat(fakeLocal.saved).isEmpty()
            assertThat(remoteCalled).isTrue()
        }

    @Test
    fun `when load is append and no last item, returns success with end of pagination false without calling remote`() =
        runTest {
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(PaginatedItems(items = items, nextPage = 1))
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)

            // Create a paging state with no last item
            val pagingState = createPagingState(pages = 0, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.APPEND, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            val success = result as MediatorResult.Success
            assertThat(success.endOfPaginationReached).isFalse()
            assertThat(remoteCalled).isFalse()

            val fakeLocal = localDataSource as FakeItemsLocalDataSource
            assertThat(fakeLocal.clearCalls).isEqualTo(0)
            assertThat(fakeLocal.saved).isEmpty()
        }

    @Test
    fun `when load is append, forwards correct page and page size to remote`() =
        runTest {
            var receivedPage: Int? = null
            var receivedPageSize: Int? = null
            remoteDataSource = FakeItemsRemoteDataSource { page, pageSize ->
                receivedPage = page.value
                receivedPageSize = pageSize.value
                Ok(PaginatedItems(items = items, nextPage = 2))
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
            val pagingState = createPagingState(pages = 1, pageSize = 2)

            itemsRemoteMediator.load(LoadType.APPEND, pagingState)

            assertThat(receivedPage).isEqualTo(2)
            assertThat(receivedPageSize).isEqualTo(2)
        }

    @Test
    fun `when load is prepend, always returns success with end of paginationReached true`() =
        runTest {
            var remoteCalled = false
            remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
                remoteCalled = true
                Ok(PaginatedItems(items = items, nextPage = 1))
            }
            itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
            val pagingState = createPagingState(pages = 1, pageSize = 2)

            val result = itemsRemoteMediator.load(LoadType.PREPEND, pagingState)

            assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
            val success = result as MediatorResult.Success
            assertThat(success.endOfPaginationReached).isTrue()
            assertThat(remoteCalled).isFalse()
        }

    @Test
    fun `when remote data source returns error, then load returns Error`() = runTest {
        val exception = IOException("Network Error")
        var remoteCalled = false
        remoteDataSource = FakeItemsRemoteDataSource { _, _ ->
            remoteCalled = true
            Err(exception)
        }
        itemsRemoteMediator = ItemsRemoteMediator(localDataSource, remoteDataSource)
        val pagingState = createPagingState(pages = 0, pageSize = 2)

        val result = itemsRemoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(MediatorResult.Error::class.java)
        assertThat((result as MediatorResult.Error).throwable).isEqualTo(exception)
        assertThat(remoteCalled).isTrue()
    }
}
