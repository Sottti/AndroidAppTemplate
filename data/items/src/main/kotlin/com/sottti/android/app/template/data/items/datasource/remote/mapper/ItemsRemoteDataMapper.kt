package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal fun ItemsApiModel.toDomain(): List<Item> =
    items.map { itemApiModel -> itemApiModel.toDomain() }

private fun ItemApiModel.toDomain(): Item =
    Item(
        id = ItemId(id),
        name = ItemName(name),
        image = ItemImage(
            description = ImageContentDescription(imageDescription),
            imageUrl = ImageUrl(imageUrl),
        ),
    )
