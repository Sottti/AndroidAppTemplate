package com.sottti.android.app.template.data.items.datasource.data

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal val item =
    Item(
        id = ItemId(ITEM_ITEM_ID),
        name = ItemName(ITEM_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(ITEM_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(ITEM_IMAGE_URL),
        ),
    )

internal val item2 =
    Item(
        id = ItemId(ITEM_ITEM_ID_2),
        name = ItemName(ITEM_ITEM_NAME_2),
        image = ItemImage(
            description = ImageContentDescription(ITEM_IMAGE_DESCRIPTION_2),
            imageUrl = ImageUrl(ITEM_IMAGE_URL_2),
        )
    )
