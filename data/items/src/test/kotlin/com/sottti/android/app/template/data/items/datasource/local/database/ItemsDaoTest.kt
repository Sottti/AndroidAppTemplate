package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.REPLACE_NAME
import com.sottti.android.app.template.data.items.datasource.data.item2RoomModel
import com.sottti.android.app.template.data.items.datasource.data.itemRoomModel
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
        dao = db.dao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert and observe items returns items`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)

        val pagingSource = dao.observeItems()
        val result = pagingSource.loadAsPage(loadParamsRefresh)

        assertThat(result.data.map { it.name })
            .isEqualTo(listOf(itemRoomModel.name, item2RoomModel.name))
    }

    @Test
    fun `clearAll deletes all items`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))
        dao.clearAll()
        val result = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(result.data).isEmpty()
    }

    @Test
    fun `observeItems on empty database returns empty list`() = runTest {
        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `insertOrUpdate with same id replaces existing row`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))

        val replaced = itemRoomModel.copy(name = REPLACE_NAME)
        dao.insertOrUpdate(listOf(replaced))

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).hasSize(1)
        assertThat(result.data.single().name).isEqualTo(REPLACE_NAME)
    }

    @Test
    fun `observeItems returns items correctly sorted by name`() = runTest {
        val third = item2RoomModel.copy(id = "item-3", name = "A Item 0")
        dao.insertOrUpdate(
            listOf(
                itemRoomModel.copy(name = "Zulu Item"),
                third,
                item2RoomModel.copy(name = "Middle Item"),
            )
        )

        val pagingSource = dao.observeItems()
        val result = pagingSource.loadAsPage(loadParamsRefresh)
        assertThat(result.data.map { it.name })
            .isEqualTo(listOf("A Item 0", "Middle Item", "Zulu Item"))
    }


    @Test
    fun `insertOrUpdate with same items twice results in no duplicates`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)
        dao.insertOrUpdate(items)

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).hasSize(2)
    }

    @Test
    fun `insertOrUpdate with batch replace replaces all rows`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)

        val replacedItems = listOf(
            itemRoomModel.copy(name = "Replaced 1"),
            item2RoomModel.copy(name = "Replaced 2"),
        )
        dao.insertOrUpdate(replacedItems)

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data.map { it.name })
            .isEqualTo(listOf("Replaced 1", "Replaced 2"))
    }
}
