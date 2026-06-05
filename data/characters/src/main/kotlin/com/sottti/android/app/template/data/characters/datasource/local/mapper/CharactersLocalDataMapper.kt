package com.sottti.android.app.template.data.characters.datasource.local.mapper

import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
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

internal fun List<Character>.toRoom(nowInMillis: Long) = map { it.toRoom(nowInMillis) }

@OptIn(ExperimentalTime::class)
internal fun Character.toRoom(nowInMillis: Long) =
    CharacterRoomModel(
        created = created?.value?.toString(),
        episodes = episodes?.map { it.value.value },
        gender = gender?.value,
        id = id.value,
        imageDescription = image?.description?.value,
        imageUrl = image?.url?.value,
        locationName = location?.name?.value,
        locationUrl = location?.url?.value,
        name = name.value,
        originName = origin?.name?.value,
        originUrl = origin?.url?.value,
        species = species?.value,
        status = status?.value,
        storedAt = nowInMillis,
        type = type?.value,
        url = url?.value,
    )

@OptIn(ExperimentalTime::class)
internal fun CharacterRoomModel.toDomain() =
    Character(
        created = created?.let { CharacterCreated(Instant.parse(it)) },
        episodes = episodes?.toDomain(),
        gender = gender?.let { CharacterGender(it) },
        id = CharacterId(id),
        image = imageUrl.toDomainImage(imageDescription),
        location = toDomainLocation(),
        name = CharacterName(name),
        origin = toDomainOrigin(),
        species = species?.let { CharacterSpecies(it) },
        status = status?.let { CharacterStatus(it) },
        type = type?.let { CharacterType(it) },
        url = url?.let { Url(it) },
    )

private fun String?.toDomainImage(description: String?): CharacterImage? =
    when {
        this == null -> null
        else -> CharacterImage(
            description = ImageContentDescription(description ?: ""),
            url = ImageUrl(this)
        )
    }

private fun CharacterRoomModel.toDomainOrigin(): CharacterOrigin? =
    when {
        originName == null && originUrl == null -> null
        else -> CharacterOrigin(
            name = originName?.let { LocationName(it) },
            url = originUrl?.let { Url(it) }
        )
    }

private fun CharacterRoomModel.toDomainLocation(): CharacterLocation? =
    when {
        locationName == null && locationUrl == null -> null
        else -> CharacterLocation(
            name = locationName?.let { LocationName(it) },
            url = locationUrl?.let { Url(it) }
        )
    }

private fun List<String>.toDomain(): List<CharacterEpisodes> =
    mapNotNull { episode ->
        if (episode.isBlank()) null else CharacterEpisodes(Url(episode))
    }
