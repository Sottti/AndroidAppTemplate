package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class RemoteKeysDaoTest {
    private lateinit var db: ItemsDatabase
    private lateinit var dao: RemoteKeysDao

    @Before
    fun setUp() {
        db = ApplicationProvider
            .getApplicationContext<Context>()
            .createDb()
        dao = db.remoteKeysDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `get returns null when database is empty`() = runTest {
        val keys = dao.get()
        assertThat(keys).isNull()
    }

    @Test
    fun `upsert inserts keys and get returns them`() = runTest {
        val keysToInsert = RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        dao.upsert(keysToInsert)

        val retrievedKeys = dao.get()
        assertThat(retrievedKeys).isNotNull()
        assertThat(retrievedKeys?.prevPage).isEqualTo(1)
        assertThat(retrievedKeys?.nextPage).isEqualTo(3)
        assertThat(retrievedKeys?.anchor).isEqualTo("items")
    }

    @Test
    fun `upsert with same anchor replaces existing keys`() = runTest {
        val initialKeys = RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        dao.upsert(initialKeys)

        val updatedKeys = RemoteKeysRoomModel(anchor = "items", nextPage = 4, prevPage = 2)
        dao.upsert(updatedKeys)

        val retrievedKeys = dao.get()
        assertThat(retrievedKeys).isNotNull()
        assertThat(retrievedKeys?.prevPage).isEqualTo(2)
        assertThat(retrievedKeys?.nextPage).isEqualTo(4)
    }

    @Test
    fun `clear deletes all keys from the database`() = runTest {
        val keysToInsert = RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        dao.upsert(keysToInsert)

        var retrievedKeys = dao.get()
        assertThat(retrievedKeys).isNotNull()

        dao.clear()

        retrievedKeys = dao.get()
        assertThat(retrievedKeys).isNull()
    }

    @Test
    fun `get uses default anchor and retrieves correct keys`() = runTest {
        val keysToInsert = RemoteKeysRoomModel(anchor = "items", nextPage = 5, prevPage = 3)
        dao.upsert(keysToInsert)

        val retrievedKeys = dao.get()

        assertThat(retrievedKeys).isNotNull()
        assertThat(retrievedKeys?.prevPage).isEqualTo(3)
        assertThat(retrievedKeys?.nextPage).isEqualTo(5)
    }

    @Test
    fun `get with different anchor returns null`() = runTest {
        val keysToInsert = RemoteKeysRoomModel(anchor = "items", nextPage = 3, prevPage = 1)
        dao.upsert(keysToInsert)

        val retrievedKeys = dao.get(anchor = "other_items")

        assertThat(retrievedKeys).isNull()
    }

    @Test
    fun `upsert allows null prev or next`() = runTest {
        dao.upsert(RemoteKeysRoomModel(anchor = "items", prevPage = null, nextPage = 2))
        val a = dao.get()
        assertThat(a?.prevPage).isNull()
        assertThat(a?.nextPage).isEqualTo(2)

        dao.upsert(RemoteKeysRoomModel(anchor = "items", prevPage = 1, nextPage = null))
        val b = dao.get()
        assertThat(b?.prevPage).isEqualTo(1)
        assertThat(b?.nextPage).isNull()
    }

    @Test
    fun `upsert with same anchor keeps single row`() = runTest {
        dao.upsert(RemoteKeysRoomModel("items", nextPage = 2, prevPage = 0))
        dao.upsert(RemoteKeysRoomModel("items", nextPage = 3, prevPage = 1))
        val count = db
            .query("SELECT COUNT(*) FROM remote_keys", null)
            .use { it.moveToFirst(); it.getInt(0) }
        assertThat(count).isEqualTo(1)
    }

    @Test
    fun `withTransaction executes block`() = runTest {
        dao.withTransaction {
            dao.upsert(RemoteKeysRoomModel("items", nextPage = 10, prevPage = 9))
        }
        assertThat(dao.get()?.nextPage).isEqualTo(10)
    }
}
