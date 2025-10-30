package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
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
class ItemsDaoTest {
    private lateinit var db: ItemsDatabase
    private lateinit var dao: ItemsDao

    @Before
    fun setUp() {
        db = ApplicationProvider
            .getApplicationContext<Context>()
            .createDb()
        dao = db.itemsDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `observe items on empty database returns empty page`() = runTest {
        val page = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(page.data).isEmpty()
    }

    @Test
    fun `upsert inserts items and observe items returns them`() = runTest {
        val itemsToInsert = listOf(fixtureItem1RoomModel, fixtureItem2RoomModel)
        dao.upsert(itemsToInsert)

        val page = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(page.data)
            .containsExactlyElementsIn(itemsToInsert)
            .inOrder()
    }

    @Test
    fun `upsert with conflicting id replaces existing item`() = runTest {
        dao.upsert(listOf(fixtureItem1RoomModel))

        val updatedItem = fixtureItem1RoomModel.copy(name = fixtureItem2RoomModel.name)
        dao.upsert(listOf(updatedItem))

        val page = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(page.data).hasSize(1)
        assertThat(page.data.first()).isEqualTo(updatedItem)
    }

    @Test
    fun `clear deletes all items from the database`() = runTest {
        val itemsToInsert = listOf(fixtureItem1RoomModel, fixtureItem2RoomModel)
        dao.upsert(itemsToInsert)

        var page = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(page.data).isNotEmpty()

        dao.clear()

        page = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(page.data).isEmpty()
    }

    @Test
    fun `observe items returns items sorted by id ascending`() = runTest {
        val itemsToInsert = listOf(fixtureItem2RoomModel, fixtureItem1RoomModel)
        dao.upsert(itemsToInsert)

        val page = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(page.data)
            .containsExactly(fixtureItem1RoomModel, fixtureItem2RoomModel)
            .inOrder()
    }

    @Test
    fun `clean and insert clears old data and inserts new data`() = runTest {
        dao.upsert(listOf(fixtureItem1RoomModel))

        val newItems = listOf(fixtureItem2RoomModel)
        dao.cleanAndInsert(newItems)

        val page = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(page.data).hasSize(1)
        assertThat(page.data.first()).isEqualTo(fixtureItem2RoomModel)
    }

    @Test
    fun `upsert with empty list is a no-op`() = runTest {
        dao.upsert(emptyList())
        val page = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(page.data).isEmpty()
    }
}
