package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.fixtures.fixtureItem1RoomModel
import com.sottti.android.app.template.data.items.datasource.local.fixtures.fixtureItem2RoomModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class ItemsDaoTest {
    private lateinit var db: ItemsDatabase
    private lateinit var dao: ItemsDao

    @Before
    fun setUp() {
        db = ApplicationProvider.getApplicationContext<Context>().createDb()
        dao = db.itemsDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `observe items on empty database returns empty list`() = runTest {
        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `upsert inserts items and observe items returns them`() = runTest {
        val itemsToInsert = listOf(fixtureItem1RoomModel, fixtureItem2RoomModel)
        dao.upsert(itemsToInsert)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot)
            .containsExactlyElementsIn(itemsToInsert)
            .inOrder()
    }

    @Test
    fun `upsert with conflicting id replaces existing item`() = runTest {
        dao.upsert(listOf(fixtureItem1RoomModel))

        val updatedItem = fixtureItem1RoomModel.copy(name = fixtureItem2RoomModel.name)
        dao.upsert(listOf(updatedItem))

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot).hasSize(1)
        assertThat(snapshot.first()).isEqualTo(updatedItem)
    }

    @Test
    fun `clear deletes all items from the database`() = runTest {
        val itemsToInsert = listOf(fixtureItem1RoomModel, fixtureItem2RoomModel)
        dao.upsert(itemsToInsert)

        var snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()
        assertThat(snapshot).isNotEmpty()

        dao.clear()

        snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()
        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `observe items returns items sorted by id ascending`() = runTest {
        val itemsToInsert = listOf(fixtureItem2RoomModel, fixtureItem1RoomModel)
        dao.upsert(itemsToInsert)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot)
            .containsExactly(fixtureItem1RoomModel, fixtureItem2RoomModel)
            .inOrder()
    }

    @Test
    fun `clean and insert clears old data and inserts new data`() = runTest {
        dao.upsert(listOf(fixtureItem1RoomModel))

        val newItems = listOf(fixtureItem2RoomModel)
        dao.cleanAndInsert(newItems)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot).hasSize(1)
        assertThat(snapshot.first()).isEqualTo(fixtureItem2RoomModel)
    }

    @Test
    fun `upsert with empty list is a no-op`() = runTest {
        dao.upsert(emptyList())

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeItems() }
            .flow
            .asSnapshot()

        assertThat(snapshot).isEmpty()
    }
}
