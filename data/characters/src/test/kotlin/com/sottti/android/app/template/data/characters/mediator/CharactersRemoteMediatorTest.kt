package com.sottti.android.app.template.data.characters.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult.Error
import androidx.paging.RemoteMediator.MediatorResult.Success
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.CharactersLocalDataSourceFake
import com.sottti.android.app.template.data.characters.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersRemoteDataSourceFake
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.domain.characters.fixtures.listOfMultipleCharacters
import com.sottti.android.app.template.domain.characters.model.Character
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
internal class CharactersRemoteMediatorTest {

    private lateinit var localDataSource: CharactersLocalDataSourceFake
    private lateinit var remoteDataSource: CharactersRemoteDataSourceFake
    private lateinit var mediator: CharactersRemoteMediator

    private val pagingState = PagingState<Int, Character>(
        pages = emptyList(),
        anchorPosition = null,
        config = PagingConfig(pageSize = 10),
        leadingPlaceholderCount = 0,
    )

    @Before
    fun setUp() {
        localDataSource = CharactersLocalDataSourceFake()
        remoteDataSource = CharactersRemoteDataSourceFake()
        mediator = CharactersRemoteMediator(
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
    fun `load with refresh type returns error when remote data source fails`() = runTest {
        val exception = Unknown("Remote error")
        localDataSource.upsert(listOfMultipleCharacters)
        remoteDataSource.setErrorResponse(exception)

        val result = mediator.load(LoadType.REFRESH, pagingState)

        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).throwable).isEqualTo(exception)
        assertThat(localDataSource.clearAllCalled).isFalse()
        assertThat(localDataSource.clearAndInsertCalled).isFalse()
        assertThat(localDataSource.saved.size).isEqualTo(listOfMultipleCharacters.size)
    }

    @Test(expected = CancellationException::class)
    fun `load with refresh type rethrows cancellation exception`() = runTest {
        remoteDataSource.setErrorResponse(CancellationException("Job cancelled"))

        mediator.load(LoadType.REFRESH, pagingState)
    }

    @Test
    fun `load with refresh type clears local data and inserts new data`() = runTest {
        localDataSource.upsert(listOfMultipleCharacters)
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters)

        mediator.load(LoadType.REFRESH, pagingState)

        assertThat(localDataSource.clearAllCalled).isTrue()
        assertThat(localDataSource.clearAndInsertCalled).isTrue()
        assertThat(localDataSource.saved.size).isEqualTo(listOfMultipleCharacters.size)
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
                anchor = "characters",
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
            RemoteKeysRoomModel(anchor = "characters", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters.take(5))

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
    fun `refresh forwards page 1 and updates remote keys`() = runTest {
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters.take(10))

        val result = mediator.load(LoadType.REFRESH, pagingState) as Success
        assertThat(result.endOfPaginationReached).isFalse()

        assertThat(remoteDataSource.lastCalledPageNumber?.value).isEqualTo(1)

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
            RemoteKeysRoomModel(anchor = "characters", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters.take(10))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isFalse()
        assertThat(remoteDataSource.lastCalledPageNumber?.value).isEqualTo(3)
        assertThat(localDataSource.remoteKeys?.nextPage).isEqualTo(4)
        assertThat(localDataSource.remoteKeys?.prevPage).isEqualTo(2)
    }

    @Test
    fun `append with short page marks end and clears next`() = runTest {
        localDataSource.remoteKeys =
            RemoteKeysRoomModel(anchor = "characters", nextPage = 3, prevPage = 1)
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters.take(5))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isTrue()
        assertThat(localDataSource.remoteKeys?.nextPage).isNull()
        assertThat(localDataSource.remoteKeys?.prevPage).isEqualTo(2)
    }

    @Test
    fun `append with no remote keys ends pagination without network call`() = runTest {
        localDataSource.remoteKeys = null
        remoteDataSource.setSuccessResponse(listOfMultipleCharacters.take(10))

        val result = mediator.load(LoadType.APPEND, pagingState) as Success
        assertThat(result.endOfPaginationReached).isTrue()
        assertThat(remoteDataSource.lastCalledPageNumber).isNull()
    }
}
