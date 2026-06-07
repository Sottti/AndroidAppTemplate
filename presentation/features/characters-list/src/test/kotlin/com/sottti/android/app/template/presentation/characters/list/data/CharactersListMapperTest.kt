package com.sottti.android.app.template.presentation.characters.list.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.listOfTwoCharacters
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState
import org.junit.Test

internal class CharactersListMapperTest {

    @Test
    fun `maps a domain character to a UI model`() {
        val domainCharacter = fixtureCharacter1

        val uiModel = domainCharacter.toUi()

        assertThat(uiModel.id).isEqualTo(domainCharacter.id.value)
        assertThat(uiModel.name).isEqualTo(domainCharacter.name.value)
        assertThat(uiModel.image).isInstanceOf(CharacterImageState.NetworkImage::class.java)
        val image = uiModel.image as CharacterImageState.NetworkImage
        assertThat(image.url).isEqualTo(domainCharacter.image?.url)
        assertThat(image.description).isEqualTo(domainCharacter.image?.description)
    }

    @Test
    fun `maps domain characters to UI models`() {
        val domainList = listOfTwoCharacters

        val uiModelList = domainList.toUi()

        assertThat(uiModelList).hasSize(2)

        assertThat(uiModelList[0].id).isEqualTo(domainList[0].id.value)
        assertThat(uiModelList[0].name).isEqualTo(domainList[0].name.value)

        assertThat(uiModelList[1].id).isEqualTo(domainList[1].id.value)
        assertThat(uiModelList[1].name).isEqualTo(domainList[1].name.value)
    }

    @Test
    fun `maps an empty domain character list to an empty UI list`() {
        val emptyDomainList = emptyList<Character>()

        val uiModelList = emptyDomainList.toUi()

        assertThat(uiModelList).isEmpty()
    }
}
