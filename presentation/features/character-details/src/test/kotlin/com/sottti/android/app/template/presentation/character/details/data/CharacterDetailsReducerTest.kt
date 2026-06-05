package com.sottti.android.app.template.presentation.character.details.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1MaxedNulls
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter2
import com.sottti.android.app.template.presentation.design.system.icons.data.Icons
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.character.details.R
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.character.details.model.CharacterImageState
import com.sottti.android.app.template.presentation.character.details.ui.loadingState
import org.junit.Test

internal class CharacterDetailsReducerTest {

    @Test
    fun `given a loading state, when update is character, then it returns a loaded state`() {
        val update = loadingState.reduce(fixtureCharacter1)

        assertThat(update).isInstanceOf(CharacterDetailsState.Loaded::class.java)
        val loadedState = update as CharacterDetailsState.Loaded

        assertThat(loadedState.character.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.character.identity.name.trailing).isEqualTo(fixtureCharacter1.name.value)
        assertThat(loadedState.character.image).isInstanceOf(CharacterImageState.NetworkImage::class.java)
        val image = loadedState.character.image as CharacterImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureCharacter1.image?.description)
        assertThat(image.url).isEqualTo(fixtureCharacter1.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureCharacter1.name.value)
    }

    @Test
    fun `given a loading state, when update is character with maxed nulls, then it returns a loaded state`() {
        val update = loadingState.reduce(fixtureCharacter1MaxedNulls)

        assertThat(update).isInstanceOf(CharacterDetailsState.Loaded::class.java)
        val loadedState = update as CharacterDetailsState.Loaded

        assertThat(loadedState.character.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.character.identity.name.trailing).isEqualTo(fixtureCharacter1MaxedNulls.name.value)
        assertThat(loadedState.character.image).isEqualTo(
            CharacterImageState.PlaceholderImage(Images.AvatarPlaceholder.state)
        )
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureCharacter1MaxedNulls.name.value)
    }

    @Test
    fun `given an error state, when reduce is called, then it returns a loaded state`() {
        val errorState = CharacterDetailsState.Error(topBarState = initialState.topBarState)
        val update = errorState.reduce(fixtureCharacter1)

        assertThat(update).isInstanceOf(CharacterDetailsState.Loaded::class.java)
        val loadedState = update as CharacterDetailsState.Loaded

        assertThat(loadedState.character.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.character.identity.name.trailing).isEqualTo(fixtureCharacter1.name.value)
        assertThat(loadedState.character.image).isInstanceOf(CharacterImageState.NetworkImage::class.java)
        val image = loadedState.character.image as CharacterImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureCharacter1.image?.description)
        assertThat(image.url).isEqualTo(fixtureCharacter1.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureCharacter1.name.value)
    }

    @Test
    fun `given a loaded state, when reduce is called with a new character, then it returns an updated loaded state`() {
        val initialLoadedState = initialState.reduce(fixtureCharacter1)
        val update = initialLoadedState.reduce(fixtureCharacter2)

        assertThat(update).isInstanceOf(CharacterDetailsState.Loaded::class.java)
        val loadedState = update as CharacterDetailsState.Loaded

        assertThat(loadedState.character.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.character.identity.name.trailing).isEqualTo(fixtureCharacter2.name.value)
        assertThat(loadedState.character.image).isInstanceOf(CharacterImageState.NetworkImage::class.java)
        val image = loadedState.character.image as CharacterImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureCharacter2.image?.description)
        assertThat(image.url).isEqualTo(fixtureCharacter2.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureCharacter2.name.value)
    }
}
