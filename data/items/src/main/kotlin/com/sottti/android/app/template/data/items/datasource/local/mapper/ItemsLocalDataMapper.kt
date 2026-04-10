package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.domain.core.models.Url
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemCreated
import com.sottti.android.app.template.domain.items.model.ItemEpisodes
import com.sottti.android.app.template.domain.items.model.ItemGender
import com.sottti.android.app.template.domain.items.model.ItemId
import com.sottti.android.app.template.domain.items.model.ItemImage
import com.sottti.android.app.template.domain.items.model.ItemLocation
import com.sottti.android.app.template.domain.items.model.ItemName
import com.sottti.android.app.template.domain.items.model.ItemOrigin
import com.sottti.android.app.template.domain.items.model.ItemSpecies
import com.sottti.android.app.template.domain.items.model.ItemStatus
import com.sottti.android.app.template.domain.items.model.ItemType
import com.sottti.android.app.template.domain.items.model.LocationName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

internal fun List<Item>.toRoom(nowInMillis: Long) = map { it.toRoom(nowInMillis) }

@OptIn(ExperimentalTime::class)
internal fun Item.toRoom(nowInMillis: Long) =
    ItemRoomModel(
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
internal fun ItemRoomModel.toDomain() =
    Item(
        created = created?.let { ItemCreated(Instant.parse(it)) },
        episodes = episodes?.toDomain(),
        gender = gender?.let { ItemGender(it) },
        id = ItemId(id),
        image = imageUrl.toDomainImage(imageDescription),
        location = toDomainLocation(),
        name = ItemName(name),
        origin = toDomainOrigin(),
        species = species?.let { ItemSpecies(it) },
        status = status?.let { ItemStatus(it) },
        type = type?.let { ItemType(it) },
        url = url?.let { Url(it) },
    )

private fun String?.toDomainImage(description: String?): ItemImage? =
    when {
        this == null -> null
        else -> ItemImage(
            description = ImageContentDescription(description ?: ""),
            url = ImageUrl(this)
        )
    }

private fun ItemRoomModel.toDomainOrigin(): ItemOrigin? =
    when {
        originName == null && originUrl == null -> null
        else -> ItemOrigin(
            name = originName?.let { LocationName(it) },
            url = originUrl?.let { Url(it) }
        )
    }

private fun ItemRoomModel.toDomainLocation(): ItemLocation? =
    when {
        locationName == null && locationUrl == null -> null
        else -> ItemLocation(
            name = locationName?.let { LocationName(it) },
            url = locationUrl?.let { Url(it) }
        )
    }

private fun List<String>.toDomain(): List<ItemEpisodes> =
    mapNotNull { episode ->
        if (episode.isBlank()) null else ItemEpisodes(Url(episode))
    }
