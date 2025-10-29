package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.fixtures.item
import com.sottti.android.app.template.data.items.datasource.fixtures.item2
import com.sottti.android.app.template.data.items.datasource.local.fixtures.item2RoomModel
import com.sottti.android.app.template.data.items.datasource.local.fixtures.itemRoomModel
import org.junit.Test

internal class ItemsLocalDataMapperTest {

    @Test
    fun `maps item to domain`() {
        assertThat(itemRoomModel.toDomain()).isEqualTo(item)
    }

    @Test
    fun `maps items to room`() {
        val items = listOf(item, item2)
        val result = items.toRoom(System.currentTimeMillis())
        assertThat(result).isEqualTo(listOf(itemRoomModel, item2RoomModel))
    }

    @Test
    fun `maps item to room`() {
        assertThat(item.toRoom(System.currentTimeMillis())).isEqualTo(itemRoomModel)
    }
}
