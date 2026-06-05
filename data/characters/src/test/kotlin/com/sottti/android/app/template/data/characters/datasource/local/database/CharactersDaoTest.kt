package com.sottti.android.app.template.data.characters.datasource.local.database

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter1RoomModel
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter2RoomModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CharactersDaoTest {
    private lateinit var db: CharactersDatabase
    private lateinit var dao: CharactersDao

    @Before
    fun setUp() {
        db = ApplicationProvider.getApplicationContext<Context>().createDb()
        dao = db.charactersDao
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `observe characters on empty database returns empty list`() = runTest {
        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `get character on empty database returns null`() = runTest {
        val character = dao.getCharacter(fixtureCharacter1RoomModel.id)

        assertThat(character).isNull()
    }

    @Test
    fun `observe character on empty database returns null`() = runTest {
        val character = dao.observeCharacter(fixtureCharacter1RoomModel.id).first()

        assertThat(character).isNull()
    }

    @Test
    fun `upsert inserts characters and observe characters returns them`() = runTest {
        val charactersToInsert = listOf(fixtureCharacter1RoomModel, fixtureCharacter2RoomModel)
        dao.upsert(charactersToInsert)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot)
            .containsExactlyElementsIn(charactersToInsert)
            .inOrder()
    }

    @Test
    fun `upsert with conflicting id replaces existing character`() = runTest {
        dao.upsert(listOf(fixtureCharacter1RoomModel))

        val updatedCharacter = fixtureCharacter1RoomModel.copy(name = fixtureCharacter2RoomModel.name)
        dao.upsert(listOf(updatedCharacter))

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot).hasSize(1)
        assertThat(snapshot.first()).isEqualTo(updatedCharacter)
    }

    @Test
    fun `clear deletes all characters from the database`() = runTest {
        val charactersToInsert = listOf(fixtureCharacter1RoomModel, fixtureCharacter2RoomModel)
        dao.upsert(charactersToInsert)

        var snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()
        assertThat(snapshot).isNotEmpty()

        dao.clear()

        snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()
        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `observe characters returns characters sorted by id ascending`() = runTest {
        val charactersToInsert = listOf(fixtureCharacter2RoomModel, fixtureCharacter1RoomModel)
        dao.upsert(charactersToInsert)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot)
            .containsExactly(fixtureCharacter1RoomModel, fixtureCharacter2RoomModel)
            .inOrder()
    }

    @Test
    fun `clean and insert clears old data and inserts new data`() = runTest {
        dao.upsert(listOf(fixtureCharacter1RoomModel))

        val newCharacters = listOf(fixtureCharacter2RoomModel)
        dao.cleanAndInsert(newCharacters)

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot).hasSize(1)
        assertThat(snapshot.first()).isEqualTo(fixtureCharacter2RoomModel)
    }

    @Test
    fun `upsert with empty list is a no-op`() = runTest {
        dao.upsert(emptyList())

        val snapshot = Pager(PagingConfig(pageSize = 10)) { dao.observeCharacters() }
            .flow
            .asSnapshot()

        assertThat(snapshot).isEmpty()
    }
}
