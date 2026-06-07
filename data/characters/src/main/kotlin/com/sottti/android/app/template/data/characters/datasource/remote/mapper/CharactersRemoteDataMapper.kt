package com.sottti.android.app.template.data.characters.datasource.remote.mapper

import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterLocationApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharactersApiModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.domain.core.models.Url
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterCreated
import com.sottti.android.app.template.domain.characters.model.CharacterEpisodes
import com.sottti.android.app.template.domain.characters.model.CharacterGender
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.domain.characters.model.CharacterImage
import com.sottti.android.app.template.domain.characters.model.CharacterLocation
import com.sottti.android.app.template.domain.characters.model.CharacterName
import com.sottti.android.app.template.domain.characters.model.CharacterOrigin
import com.sottti.android.app.template.domain.characters.model.CharacterSpecies
import com.sottti.android.app.template.domain.characters.model.CharacterStatus
import com.sottti.android.app.template.domain.characters.model.CharacterType
import com.sottti.android.app.template.domain.characters.model.LocationName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal fun CharactersApiModel.toDomain(): List<Character> =
    results.map { characterApiModel ->
        characterApiModel.toDomain()
    }

@OptIn(ExperimentalTime::class)
internal fun CharacterApiModel.toDomain(): Character = Character(
    id = CharacterId(id),
    name = CharacterName(name),
    status = status.takeIf { it.isNotBlank() }?.let { CharacterStatus(it) },
    species = species.takeIf { it.isNotBlank() }?.let { CharacterSpecies(it) },
    image = image.toDomain(name),
    type = type.takeIf { it.isNotBlank() }?.let { CharacterType(it) },
    gender = gender.takeIf { it.isNotBlank() }?.let { CharacterGender(it) },
    origin = origin.toDomainOrigin(),
    location = location.toDomainLocation(),
    episodes = episode.toDomain(),
    url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
    created = created.takeIf { it.isNotBlank() }?.let { CharacterCreated(Instant.parse(it)) }
)

private fun String.toDomain(name: String): CharacterImage? =
    when {
        this.isBlank() -> null
        else -> CharacterImage(
            description = ImageContentDescription("An image of $name"),
            url = ImageUrl(this),
        )
    }

private fun CharacterLocationApiModel.toDomainOrigin(): CharacterOrigin? =
    when {
        name.isBlank() && url.isBlank() -> null
        else -> CharacterOrigin(
            name = name.takeIf { it.isNotBlank() }?.let { LocationName(it) },
            url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
        )
    }

private fun CharacterLocationApiModel.toDomainLocation(): CharacterLocation? =
    when {
        name.isBlank() && url.isBlank() -> null
        else -> CharacterLocation(
            name = name.takeIf { it.isNotBlank() }?.let { LocationName(it) },
            url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
        )
    }

private fun List<String>.toDomain(): List<CharacterEpisodes> =
    mapNotNull { episode ->
        if (episode.isBlank()) null else CharacterEpisodes(Url(episode))
    }
