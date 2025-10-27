package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.sottti.android.app.template.data.items.datasource.model.PaginatedItems
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PaginatedItemsApiModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal fun PaginatedItemsApiModel.toDomain(): PaginatedItems =
    PaginatedItems(
        items = items.map { it.toDomain() },
        nextPage = nextPage,
    )

private fun ItemApiModel.toDomain(): Item =
    Item(
        id = ItemId(id),
        name = ItemName(name),
        image = ItemImage(
            description = ImageContentDescription(imageDescription),
            imageUrl = ImageUrl(imageUrl),
        ),
    )
