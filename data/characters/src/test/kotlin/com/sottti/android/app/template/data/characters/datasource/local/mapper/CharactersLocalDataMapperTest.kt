package com.sottti.android.app.template.data.characters.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.TimeProviderFake
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter1RoomModel
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter2RoomModel
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter2
import com.sottti.android.app.template.domain.characters.model.Character
import org.junit.Test

private val now = TimeProviderFake().now()

internal class CharactersLocalDataMapperTest {

    @Test
    fun `maps character to domain`() {
        assertThat(fixtureCharacter1RoomModel.toDomain()).isEqualTo(fixtureCharacter1)
    }

    @Test
    fun `maps characters to room`() {
        val characters = listOf(fixtureCharacter1, fixtureCharacter2)
        val result = characters.toRoom(now)
        assertThat(result).isEqualTo(listOf(fixtureCharacter1RoomModel, fixtureCharacter2RoomModel))
    }

    @Test
    fun `maps character to room`() {
        assertThat(fixtureCharacter1.toRoom(now)).isEqualTo(fixtureCharacter1RoomModel)
    }

    @Test
    fun `room to domain to room is lossless`() {
        val original = fixtureCharacter1RoomModel
        val roundTrip = original.toDomain().toRoom(now)
        assertThat(roundTrip).isEqualTo(original)
    }

    @Test
    fun `domain to room to domain is lossless`() {
        val original = fixtureCharacter1
        val roundTrip = original.toRoom(now).toDomain()
        assertThat(roundTrip).isEqualTo(original)
    }

    @Test
    fun `maps empty list to empty list`() {
        assertThat(emptyList<Character>().toRoom(now)).isEmpty()
    }
}
