package com.sottti.android.app.template.data.items.datasource.local

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDaoFake
import com.sottti.android.app.template.data.items.datasource.local.database.RemoteKeysDaoFake
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ItemsLocalDataSourceImplTest {

    private lateinit var itemsDao: ItemsDaoFake
    private lateinit var localDataSource: ItemsLocalDataSource
    private lateinit var remoteKeysDao: RemoteKeysDaoFake
    private lateinit var timeProvider: TimeProviderFake

    @Before
    fun setUp() {
        itemsDao = ItemsDaoFake()
        remoteKeysDao = RemoteKeysDaoFake()
        timeProvider = TimeProviderFake()
        localDataSource = ItemsLocalDataSourceImpl(
            itemsDao = itemsDao,
            remoteKeysDao = remoteKeysDao,
            timeProvider = timeProvider,
        )
    }

    @Test
    fun `upsert calls dao upsert with mapped items`() = runTest {
        localDataSource.upsert(listOf(fixtureItem1))

        assertThat(itemsDao.upsertCalled).isTrue()
        assertThat(itemsDao.saved.first()).isEqualTo(fixtureItem1.toRoom(timeProvider.now()))
    }

    @Test
    fun `clear and insert calls dao clear and then dao upsert`() = runTest {
        val itemsToInsert = listOf(fixtureItem1)
        localDataSource.clearAndInsert(itemsToInsert)

        assertThat(itemsDao.clearCalled).isTrue()
        assertThat(itemsDao.upsertCalled).isTrue()
        assertThat(itemsDao.saved.first()).isEqualTo(fixtureItem1.toRoom(timeProvider.now()))
    }

    @Test
    fun `clearAll calls clear on both daos`() = runTest {
        localDataSource.clearAll()

        assertThat(itemsDao.clearCalled).isTrue()
        assertThat(remoteKeysDao.clearCalled).isTrue()
    }

    @Test
    fun `get next remote page calls dao get`() = runTest {
        remoteKeysDao.keys = RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        val result = localDataSource.getNextRemotePage()

        assertThat(remoteKeysDao.getCalled).isTrue()
        assertThat(result?.nextPage).isEqualTo(3)
    }

    @Test
    fun `update remote keys calls dao upsert with correct keys`() = runTest {
        localDataSource.updateRemoteKeys(nextPage = 5, prevPage = 3)

        assertThat(remoteKeysDao.upsertCalled).isTrue()
        assertThat(remoteKeysDao.keys?.nextPage).isEqualTo(5)
        assertThat(remoteKeysDao.keys?.prevPage).isEqualTo(3)
    }

    @Test
    fun `run transaction calls with transaction on dao`() = runTest {
        var blockExecuted = false
        localDataSource.runTransaction {
            blockExecuted = true
        }

        assertThat(remoteKeysDao.transactionBlockExecuted).isTrue()
        assertThat(blockExecuted).isTrue()
    }

    @Test
    fun `updateRemoteKeys sets anchor to items`() = runTest {
        localDataSource.updateRemoteKeys(nextPage = 5, prevPage = 3)
        assertThat(remoteKeysDao.keys?.anchor).isEqualTo("items")
    }

    @Test
    fun `updateRemoteKeys supports null prev or next`() = runTest {
        localDataSource.updateRemoteKeys(nextPage = null, prevPage = 2)
        assertThat(remoteKeysDao.keys?.nextPage).isNull()
        assertThat(remoteKeysDao.keys?.prevPage).isEqualTo(2)

        localDataSource.updateRemoteKeys(nextPage = 7, prevPage = null)
        assertThat(remoteKeysDao.keys?.nextPage).isEqualTo(7)
        assertThat(remoteKeysDao.keys?.prevPage).isNull()
    }

    @Test
    fun `upsert with empty list is no-op`() = runTest {
        localDataSource.upsert(emptyList())
        assertThat(itemsDao.saved).isEmpty()
    }

    @Test(expected = IllegalStateException::class)
    fun `runTransaction propagates exception from block`() = runTest {
        localDataSource.runTransaction { throw IllegalStateException("boom") }
    }
}
