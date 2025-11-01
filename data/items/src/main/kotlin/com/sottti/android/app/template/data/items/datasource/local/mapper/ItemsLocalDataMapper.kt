package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemDescription
import com.sottti.android.app.template.domain.items.model.ItemId
import com.sottti.android.app.template.domain.items.model.ItemImage
import com.sottti.android.app.template.domain.items.model.ItemName
import com.sottti.android.app.template.domain.items.model.ItemYear

internal fun ItemRoomModel.toDomain() =
    Item(
        id = ItemId(id),
        name = ItemName(name),
        image = ItemImage(
            description = ImageContentDescription(imageDescription),
            imageUrl = ImageUrl(imageUrl),
        ),
        tagline = ItemDescription(tagline),
        year = ItemYear(year)
    )

internal fun List<Item>.toRoom(
    nowInMillis: Long,
) = map { it.toRoom(nowInMillis) }

internal fun Item.toRoom(nowInMillis: Long) =
    ItemRoomModel(
        id = id.value,
        imageDescription = image.description.value,
        imageUrl = image.imageUrl.value,
        name = name.value,
        storedAt = nowInMillis,
        tagline = tagline.value,
        year = year.value,
    )
