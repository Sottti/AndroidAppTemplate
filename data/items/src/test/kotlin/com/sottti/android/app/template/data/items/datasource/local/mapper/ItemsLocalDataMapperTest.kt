package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.TimeProviderFake
import com.sottti.android.app.template.data.items.datasource.local.fixtures.fixtureItem1RoomModel
import com.sottti.android.app.template.data.items.datasource.local.fixtures.fixtureItem2RoomModel
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.fixtures.fixtureItem2
import com.sottti.android.app.template.model.Item
import org.junit.Test

private val now = TimeProviderFake().now()

internal class ItemsLocalDataMapperTest {

    @Test
    fun `maps item to domain`() {
        assertThat(fixtureItem1RoomModel.toDomain()).isEqualTo(fixtureItem1)
    }

    @Test
    fun `maps items to room`() {
        val items = listOf(fixtureItem1, fixtureItem2)
        val result = items.toRoom(now)
        assertThat(result).isEqualTo(listOf(fixtureItem1RoomModel, fixtureItem2RoomModel))
    }

    @Test
    fun `maps item to room`() {
        assertThat(fixtureItem1.toRoom(now)).isEqualTo(fixtureItem1RoomModel)
    }

    @Test
    fun `room to domain to room is lossless`() {
        val original = fixtureItem1RoomModel
        val roundTrip = original.toDomain().toRoom(now)
        assertThat(roundTrip).isEqualTo(original)
    }

    @Test
    fun `domain to room to domain is lossless`() {
        val original = fixtureItem1
        val roundTrip = original.toRoom(now).toDomain()
        assertThat(roundTrip).isEqualTo(original)
    }

    @Test
    fun `maps empty list to empty list`() {
        assertThat(emptyList<Item>().toRoom(now)).isEmpty()
    }
}
