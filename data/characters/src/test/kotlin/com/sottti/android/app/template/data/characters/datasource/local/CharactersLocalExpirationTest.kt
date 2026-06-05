package com.sottti.android.app.template.data.characters.datasource.local

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.fixtures.fixtureCharacter1RoomModel
import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
import org.junit.Test
import kotlin.time.Duration.Companion.minutes

internal class CharactersLocalExpirationTest {

    private val expirationMs = 30.minutes.inWholeMilliseconds

    @Test
    fun `is expired returns false when now is before storedAt (clock skew)`() {
        val character = fixtureCharacter1RoomModel.storedAt(storedAt = 1_000L)
        val now = 500L

        val expired = character.isExpired(now)

        assertThat(expired).isFalse()
    }

    @Test
    fun `is expired returns false just before threshold`() {
        val storedAt = 0L
        val character = fixtureCharacter1RoomModel.storedAt(storedAt)
        val now = storedAt + expirationMs - 1

        val expired = character.isExpired(now)

        assertThat(expired).isFalse()
    }

    @Test
    fun `is expired returns true exactly at threshold`() {
        val storedAt = 42L
        val character = fixtureCharacter1RoomModel.storedAt(storedAt)
        val now = storedAt + expirationMs

        val expired = character.isExpired(now)

        assertThat(expired).isTrue()
    }

    @Test
    fun `is expired returns true after threshold`() {
        val storedAt = 123_456L
        val character = fixtureCharacter1RoomModel.storedAt(storedAt)
        val now = storedAt + expirationMs + 1

        val expired = character.isExpired(now)

        assertThat(expired).isTrue()
    }

    private fun CharacterRoomModel.storedAt(storedAt: Long): CharacterRoomModel =
        copy(storedAt = storedAt)
}
