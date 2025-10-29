package com.sottti.android.app.template.fixtures

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

public val fixtureItem: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL),
        ),
    )

public val fixtureItem2: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_2),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_2),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_2),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_2),
        )
    )

internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION = "Description for the test image"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_2 = "Description for the test image 2"
internal const val FIXTURE_ITEM_IMAGE_URL = "http://example.com/image.png"
internal const val FIXTURE_ITEM_IMAGE_URL_2 = "http://example.com/image2.png"
internal const val FIXTURE_ITEM_ITEM_ID = "item-1"
internal const val FIXTURE_ITEM_ITEM_ID_2 = "item-2"
internal const val FIXTURE_ITEM_ITEM_ID_3 = "item-3"
internal const val FIXTURE_ITEM_ITEM_NAME = "Test Item Name"
internal const val FIXTURE_ITEM_ITEM_NAME_2 = "Test Item Name 2"
internal const val FIXTURE_ITEM_ITEM_NAME_3 = "A Item 0"
internal const val FIXTURE_ITEM_ITEM_NAME_MIDDLE = "Middle Item"
internal const val FIXTURE_ITEM_ITEM_NAME_ONLY_ONE = "Only One"
internal const val FIXTURE_ITEM_ITEM_NAME_ZULU = "Zulu Item"
internal const val FIXTURE_REPLACED_NAME_1 = "Replaced 1"
internal const val FIXTURE_REPLACED_NAME_2 = "Replaced 2"
