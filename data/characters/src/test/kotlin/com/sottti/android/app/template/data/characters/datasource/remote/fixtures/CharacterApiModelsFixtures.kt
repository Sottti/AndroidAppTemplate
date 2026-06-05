package com.sottti.android.app.template.data.characters.datasource.remote.fixtures

import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterLocationApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharactersApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageInfoApiModel
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter2
import com.sottti.android.app.template.domain.characters.model.Character
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
private fun Character.toApiModel(): CharacterApiModel =
    CharacterApiModel(
        id = id.value,
        name = name.value,
        status = status?.value.orEmpty(),
        species = species?.value.orEmpty(),
        type = type?.value.orEmpty(),
        gender = gender?.value.orEmpty(),
        origin = CharacterLocationApiModel(
            name = origin?.name?.value.orEmpty(),
            url = origin?.url?.value.orEmpty(),
        ),
        location = CharacterLocationApiModel(
            name = location?.name?.value.orEmpty(),
            url = location?.url?.value.orEmpty(),
        ),
        image = image?.url?.value.orEmpty(),
        episode = episodes?.map { episode -> episode.value.value }.orEmpty(),
        url = url?.value.orEmpty(),
        created = created?.value?.toString().orEmpty(),
    )

internal val fixtureCharacter1ApiModel = fixtureCharacter1.toApiModel()

internal val fixtureCharacter2ApiModel = fixtureCharacter2.toApiModel()

internal val listOfTwoApiModels = listOf(
    fixtureCharacter1ApiModel,
    fixtureCharacter2ApiModel,
)

internal val fixtureCharactersApiModel = CharactersApiModel(
    info = PageInfoApiModel(
        count = listOfTwoApiModels.size,
        pages = 1,
        next = null,
        prev = null,
    ),
    results = listOfTwoApiModels,
)
