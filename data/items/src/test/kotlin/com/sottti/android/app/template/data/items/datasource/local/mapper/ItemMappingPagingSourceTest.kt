package com.sottti.android.app.template.data.items.datasource.local.mapper

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.item
import com.sottti.android.app.template.data.items.datasource.data.item2
import com.sottti.android.app.template.data.items.datasource.data.item2RoomModel
import com.sottti.android.app.template.data.items.datasource.data.itemRoomModel
import com.sottti.android.app.template.data.items.datasource.local.mapper.fakes.FakeRoomPagingSource
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ItemMappingPagingSourceTest {

    @Test
    fun `maps page data and preserves keys + counts`() = runTest {
        val prevKey = 4
        val nextKey = 6
        val itemsBefore = 8
        val itemsAfter = 12

        val roomPage = PagingSource.LoadResult.Page(
            data = listOf(itemRoomModel, item2RoomModel),
            prevKey = prevKey,
            nextKey = nextKey,
            itemsBefore = itemsBefore,
            itemsAfter = itemsAfter,
        )
        val roomPagingSource = FakeRoomPagingSource { roomPage }

        val page = ItemMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false,
            )
        ) as PagingSource.LoadResult.Page

        assertThat(page.data.map { it.id.value })
            .isEqualTo(listOf(itemRoomModel.id, item2RoomModel.id))

        assertThat(page.data.map { it.name.value })
            .isEqualTo(listOf(itemRoomModel.name, item2RoomModel.name))

        assertThat(page.prevKey).isEqualTo(prevKey)
        assertThat(page.nextKey).isEqualTo(nextKey)
        assertThat(page.itemsBefore).isEqualTo(itemsBefore)
        assertThat(page.itemsAfter).isEqualTo(itemsAfter)
    }

    @Test
    fun `get refresh key mirrors anchor logic`() {
        val mapper = ItemMappingPagingSource(
            FakeRoomPagingSource { PagingSource.LoadResult.Invalid() }
        )

        val page1 = PagingSource.LoadResult.Page(
            data = listOf(item), prevKey = null, nextKey = 2, itemsBefore = 0, itemsAfter = 1
        )
        val page2 = PagingSource.LoadResult.Page(
            data = listOf(item2), prevKey = 1, nextKey = 3, itemsBefore = 1, itemsAfter = 0
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
        val roomPagingSource = FakeRoomPagingSource { PagingSource.LoadResult.Error(boom) }

        val result = ItemMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        ) as PagingSource.LoadResult.Error

        assertThat(result.throwable).isSameInstanceAs(boom)
    }

    @Test
    fun `propagates invalid`() = runTest {
        val roomPagingSource = FakeRoomPagingSource { PagingSource.LoadResult.Invalid() }

        val result = ItemMappingPagingSource(roomPagingSource).load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Invalid::class.java)
    }

    @Test
    fun `when underlying source is invalid, then load returns invalid`() = runTest {
        val roomPagingSource = FakeRoomPagingSource {
            PagingSource.LoadResult.Page(emptyList(), null, null, 0, 0)
        }
        val mapper = ItemMappingPagingSource(roomPagingSource)

        roomPagingSource.invalidate()
        assertThat(mapper.invalid).isTrue()

        val result = mapper.load(
            PagingSource.LoadParams.Refresh(key = null, loadSize = 10, placeholdersEnabled = false)
        )

        assertThat(result).isInstanceOf(PagingSource.LoadResult.Invalid::class.java)
    }

}
