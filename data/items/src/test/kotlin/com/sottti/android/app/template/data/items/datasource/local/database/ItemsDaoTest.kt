package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_ID_3
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_3
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_MIDDLE
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_ONLY_ONE
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_ZULU
import com.sottti.android.app.template.data.items.datasource.data.REPLACED_NAME_1
import com.sottti.android.app.template.data.items.datasource.data.REPLACED_NAME_2
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
    fun `inserting and observing items returns them`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)

        val pagingSource = dao.observeItems()
        val result = pagingSource.loadAsPage(loadParamsRefresh)

        assertThat(result.data.map { it.name })
            .isEqualTo(listOf(itemRoomModel.name, item2RoomModel.name))
    }

    @Test
    fun `deleting all items leaves database empty`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))
        dao.clearAll()
        val result = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(result.data).isEmpty()
    }

    @Test
    fun `observing items on empty database returns empty list`() = runTest {
        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `inserting with same id replaces the existing row`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))

        val replaced = itemRoomModel.copy(name = REPLACED_NAME_1)
        dao.insertOrUpdate(listOf(replaced))

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).hasSize(1)
        assertThat(result.data.single().name).isEqualTo(REPLACED_NAME_1)
    }

    @Test
    fun `observing items returns them sorted by name`() = runTest {
        val third = item2RoomModel.copy(id = ITEM_ITEM_ID_3, name = ITEM_ITEM_NAME_3)
        dao.insertOrUpdate(
            listOf(
                itemRoomModel.copy(name = ITEM_ITEM_NAME_ZULU),
                third,
                item2RoomModel.copy(name = ITEM_ITEM_NAME_MIDDLE),
            )
        )

        val pagingSource = dao.observeItems()
        val result = pagingSource.loadAsPage(loadParamsRefresh)
        assertThat(result.data.map { it.name })
            .isEqualTo(listOf(ITEM_ITEM_NAME_3, ITEM_ITEM_NAME_MIDDLE, ITEM_ITEM_NAME_ZULU))
    }


    @Test
    fun `inserting same items twice does not create duplicates`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)
        dao.insertOrUpdate(items)

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).hasSize(2)
    }

    @Test
    fun `batch insert replaces all rows`() = runTest {
        val items = listOf(itemRoomModel, item2RoomModel)
        dao.insertOrUpdate(items)

        val replacedItems = listOf(
            itemRoomModel.copy(name = REPLACED_NAME_1),
            item2RoomModel.copy(name = REPLACED_NAME_2),
        )
        dao.insertOrUpdate(replacedItems)

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data.map { it.name })
            .isEqualTo(listOf(REPLACED_NAME_1, REPLACED_NAME_2))
    }

    @Test
    fun `replacing with clear option removes all previous content`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel, item2RoomModel))

        val newItem = item2RoomModel.copy(id = ITEM_ITEM_ID_3, name = ITEM_ITEM_NAME_ONLY_ONE)
        dao.clearAndInsertOrUpdate(clearExisting = true, items = listOf(newItem))

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data.map { itemRoomModel -> itemRoomModel.id to itemRoomModel.name })
            .isEqualTo(listOf(ITEM_ITEM_ID_3 to ITEM_ITEM_NAME_ONLY_ONE))
    }

    @Test
    fun `inserting without clearing merges and upserts`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))

        val updated = itemRoomModel.copy(name = REPLACED_NAME_1)
        val extra = item2RoomModel
        dao.clearAndInsertOrUpdate(clearExisting = false, items = listOf(updated, extra))

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(result.data.map { itemRoomModel -> itemRoomModel.id }.toSet())
            .isEqualTo(setOf(itemRoomModel.id, item2RoomModel.id))

        assertThat(result.data.first { it.id == itemRoomModel.id }.name)
            .isEqualTo(REPLACED_NAME_1)
    }

    @Test
    fun `inserting an empty list does nothing`() = runTest {
        dao.insertOrUpdate(emptyList())

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `clearing with empty replacement list empties the table`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel))

        dao.clearAndInsertOrUpdate(clearExisting = true, items = emptyList())

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `inserting without clearing keeps rows not in input`() = runTest {
        dao.insertOrUpdate(listOf(itemRoomModel, item2RoomModel))

        val onlyFirstUpdated = itemRoomModel.copy(name = REPLACED_NAME_1)
        dao.clearAndInsertOrUpdate(clearExisting = false, items = listOf(onlyFirstUpdated))

        val result = dao.observeItems().loadAsPage(loadParamsRefresh)

        assertThat(result.data.map { it.name }.toSet())
            .isEqualTo(setOf(REPLACED_NAME_1, item2RoomModel.name))
    }
}
