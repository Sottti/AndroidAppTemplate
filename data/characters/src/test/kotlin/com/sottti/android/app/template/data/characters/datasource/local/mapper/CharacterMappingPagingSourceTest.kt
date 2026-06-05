package com.sottti.android.app.template.data.characters.datasource.local.mapper

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter1RoomModel
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter2RoomModel
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter2
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class CharacterMappingPagingSourceTest {

    @Test
    fun `maps page data and preserves keys + counts`() = runTest {
        val prevKey = 4
        val nextKey = 6
        val itemsBefore = 8
        val itemsAfter = 12

        val roomPage = PagingSource.LoadResult.Page(
            data = listOf(fixtureCharacter1RoomModel, fixtureCharacter2RoomModel),
            prevKey = prevKey,
            nextKey = nextKey,
            itemsBefore = itemsBefore,
            itemsAfter = itemsAfter,
        )
        val roomPagingSource = RoomPagingSourceFake { roomPage }

        val page = CharacterMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false,
            )
        ) as PagingSource.LoadResult.Page

        assertThat(page.data.map { it.id.value })
            .isEqualTo(listOf(fixtureCharacter1RoomModel.id, fixtureCharacter2RoomModel.id))

        assertThat(page.data.map { it.name.value })
            .isEqualTo(listOf(fixtureCharacter1RoomModel.name, fixtureCharacter2RoomModel.name))

        assertThat(page.prevKey).isEqualTo(prevKey)
        assertThat(page.nextKey).isEqualTo(nextKey)
        assertThat(page.itemsBefore).isEqualTo(itemsBefore)
        assertThat(page.itemsAfter).isEqualTo(itemsAfter)
    }

    @Test
    fun `get refresh key mirrors anchor logic`() {
        val mapper = CharacterMappingPagingSource(
            RoomPagingSourceFake { PagingSource.LoadResult.Invalid() }
        )

        val page1 = PagingSource.LoadResult.Page(
            data = listOf(fixtureCharacter1),
            prevKey = null,
            nextKey = 2,
            itemsBefore = 0,
            itemsAfter = 1
        )
        val page2 = PagingSource.LoadResult.Page(
            data = listOf(fixtureCharacter2),
            prevKey = 1,
            nextKey = 3,
            itemsBefore = 1,
            itemsAfter = 0,
        )

        val state = PagingState(
            pages = listOf(page1, page2),
            anchorPosition = 1,
            config = PagingConfig(pageSize = 10),
            leadingPlaceholderCount = 0
        )

        // closest page has prevKey = 1 ⇒ expect 2 (prevKey + 1)
        assertThat(mapper.getRefreshKey(state)).isEqualTo(2)
    }

    @Test
    fun `propagates error`() = runTest {
        val boom = IllegalStateException("boom")
        val roomPagingSource = RoomPagingSourceFake { PagingSource.LoadResult.Error(boom) }

        val result = CharacterMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        ) as PagingSource.LoadResult.Error

        assertThat(result.throwable).isSameInstanceAs(boom)
    }

    @Test
    fun `propagates invalid`() = runTest {
        val roomPagingSource = RoomPagingSourceFake { PagingSource.LoadResult.Invalid() }

        val result = CharacterMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Invalid::class.java)
    }

    @Test
    fun `when underlying source is invalid, then load returns invalid`() = runTest {
        val roomPagingSource = RoomPagingSourceFake {
            PagingSource.LoadResult.Page(emptyList(), null, null, 0, 0)
        }
        val mapper = CharacterMappingPagingSource(roomPagingSource)

        roomPagingSource.invalidate()
        assertThat(mapper.invalid).isTrue()

        val result = mapper.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Invalid::class.java)
    }

    @Test
    fun `getRefreshKey falls back to nextKey - 1 when prevKey null`() {
        val mapper = CharacterMappingPagingSource(
            RoomPagingSourceFake { PagingSource.LoadResult.Invalid() }
        )
        val page = PagingSource.LoadResult.Page(
            data = listOf(fixtureCharacter1),
            prevKey = null,
            nextKey = 5,
            itemsBefore = 0,
            itemsAfter = 0,
        )
        val state = PagingState(
            pages = listOf(page),
            anchorPosition = 0,
            config = PagingConfig(10),
            leadingPlaceholderCount = 0
        )
        assertThat(mapper.getRefreshKey(state)).isEqualTo(4)
    }
}
