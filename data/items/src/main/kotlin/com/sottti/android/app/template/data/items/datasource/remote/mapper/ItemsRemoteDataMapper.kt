package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemLocationApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
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

internal fun ItemsApiModel.toDomain(): List<Item> = results.map { itemApiModel -> itemApiModel.toDomain() }

@OptIn(ExperimentalTime::class)
internal fun ItemApiModel.toDomain(): Item = Item(
    id = ItemId(id),
    name = ItemName(name),
    status = status.takeIf { it.isNotBlank() }?.let { ItemStatus(it) },
    species = species.takeIf { it.isNotBlank() }?.let { ItemSpecies(it) },
    image = image.toDomain(name),
    type = type.takeIf { it.isNotBlank() }?.let { ItemType(it) },
    gender = gender.takeIf { it.isNotBlank() }?.let { ItemGender(it) },
    origin = origin.toDomainOrigin(),
    location = location.toDomainLocation(),
    episodes = episode.toDomain(),
    url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
    created = created.takeIf { it.isNotBlank() }?.let { ItemCreated(Instant.parse(it)) }
)

private fun String.toDomain(name: String): ItemImage? =
    when {
        this.isBlank() -> null
        else -> ItemImage(
            description = ImageContentDescription("An image of $name"),
            url = ImageUrl(this),
        )
    }

private fun ItemLocationApiModel.toDomainOrigin(): ItemOrigin? =
    when {
        name.isBlank() && url.isBlank() -> null
        else -> ItemOrigin(
            name = name.takeIf { it.isNotBlank() }?.let { LocationName(it) },
            url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
        )
    }

private fun ItemLocationApiModel.toDomainLocation(): ItemLocation? =
    when {
        name.isBlank() && url.isBlank() -> null
        else -> ItemLocation(
            name = name.takeIf { it.isNotBlank() }?.let { LocationName(it) },
            url = url.takeIf { it.isNotBlank() }?.let { Url(it) },
        )
    }

private fun List<String>.toDomain(): List<ItemEpisodes> =
    mapNotNull { episode ->
        if (episode.isBlank()) null else ItemEpisodes(Url(episode))
    }
