package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal fun ItemRoomModel.toDomain() =
    Item(
        id = ItemId(id),
        name = ItemName(name),
        image = ItemImage(
            description = ImageContentDescription(imageDescription),
            imageUrl = ImageUrl(imageUrl),
        )
    )

internal fun List<Item>.toRoom(now: Long) = map { it.toRoom(now) }

internal fun Item.toRoom(now: Long) =
    ItemRoomModel(
        id = id.value,
        name = name.value,
        imageUrl = image.imageUrl.value,
        imageDescription = image.description.value,
        storedAt = now,
    )
