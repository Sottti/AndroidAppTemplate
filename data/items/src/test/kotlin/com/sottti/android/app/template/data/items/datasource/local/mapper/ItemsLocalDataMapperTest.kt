package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.fixtures.item2RoomModel
import com.sottti.android.app.template.data.items.datasource.local.fixtures.itemRoomModel
import com.sottti.android.app.template.fixtures.fixtureItem
import com.sottti.android.app.template.fixtures.fixtureItem2
import org.junit.Test

internal class ItemsLocalDataMapperTest {

    @Test
    fun `maps item to domain`() {
        assertThat(itemRoomModel.toDomain()).isEqualTo(fixtureItem)
    }

    @Test
    fun `maps items to room`() {
        val items = listOf(fixtureItem, fixtureItem2)
        val result = items.toRoom()
        assertThat(result).isEqualTo(listOf(itemRoomModel, item2RoomModel))
    }

    @Test
    fun `maps item to room`() {
        assertThat(fixtureItem.toRoom()).isEqualTo(itemRoomModel)
    }
}
