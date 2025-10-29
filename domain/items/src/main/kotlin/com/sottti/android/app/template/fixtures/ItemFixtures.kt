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

public val fixtureItem3: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_3),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_3),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_3),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_3),
        )
    )

public val fixtureItem4: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_4),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_4),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_4),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_4),
        )
    )

public val fixtureItem5: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_5),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_5),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_5),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_5),
        )
    )

public val fixtureItem6: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_6),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_6),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_6),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_6),
        )
    )

public val fixtureItem7: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_7),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_7),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_7),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_7),
        )
    )

public val fixtureItem8: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_8),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_8),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_8),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_8),
        )
    )

public val fixtureItem9: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_9),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_9),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_9),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_9),
        )
    )

public val fixtureItem10: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_ITEM_ID_10),
        name = ItemName(FIXTURE_ITEM_ITEM_NAME_10),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_IMAGE_DESCRIPTION_10),
            imageUrl = ImageUrl(FIXTURE_ITEM_IMAGE_URL_10),
        )
    )

public val listOfTwoItems: List<Item> = listOf(fixtureItem, fixtureItem2)
public val listOfMultipleItems: List<Item> = listOf(
    fixtureItem,
    fixtureItem2,
    fixtureItem3,
    fixtureItem4,
    fixtureItem5,
    fixtureItem6,
    fixtureItem7,
    fixtureItem8,
    fixtureItem9,
    fixtureItem10,
)


internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION = "Description for the test image"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_10 = "Description for test image 10"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_2 = "Description for the test image 2"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_3 = "Description for test image 3"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_4 = "Description for test image 4"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_5 = "Description for test image 5"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_6 = "Description for test image 6"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_7 = "Description for test image 7"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_8 = "Description for test image 8"
internal const val FIXTURE_ITEM_IMAGE_DESCRIPTION_9 = "Description for test image 9"
internal const val FIXTURE_ITEM_IMAGE_URL = "http://example.com/image.png"
internal const val FIXTURE_ITEM_IMAGE_URL_10 = "http://example.com/image10.png"
internal const val FIXTURE_ITEM_IMAGE_URL_2 = "http://example.com/image2.png"
internal const val FIXTURE_ITEM_IMAGE_URL_3 = "http://example.com/image3.png"
internal const val FIXTURE_ITEM_IMAGE_URL_4 = "http://example.com/image4.png"
internal const val FIXTURE_ITEM_IMAGE_URL_5 = "http://example.com/image5.png"
internal const val FIXTURE_ITEM_IMAGE_URL_6 = "http://example.com/image6.png"
internal const val FIXTURE_ITEM_IMAGE_URL_7 = "http://example.com/image7.png"
internal const val FIXTURE_ITEM_IMAGE_URL_8 = "http://example.com/image8.png"
internal const val FIXTURE_ITEM_IMAGE_URL_9 = "http://example.com/image9.png"
internal const val FIXTURE_ITEM_ITEM_ID = "item-1"
internal const val FIXTURE_ITEM_ITEM_ID_10 = "item-10"
internal const val FIXTURE_ITEM_ITEM_ID_2 = "item-2"
internal const val FIXTURE_ITEM_ITEM_ID_3 = "item-3"
internal const val FIXTURE_ITEM_ITEM_ID_4 = "item-4"
internal const val FIXTURE_ITEM_ITEM_ID_5 = "item-5"
internal const val FIXTURE_ITEM_ITEM_ID_6 = "item-6"
internal const val FIXTURE_ITEM_ITEM_ID_7 = "item-7"
internal const val FIXTURE_ITEM_ITEM_ID_8 = "item-8"
internal const val FIXTURE_ITEM_ITEM_ID_9 = "item-9"
internal const val FIXTURE_ITEM_ITEM_NAME = "Test Item Name"
internal const val FIXTURE_ITEM_ITEM_NAME_10 = "Test Item Name 10"
internal const val FIXTURE_ITEM_ITEM_NAME_2 = "Test Item Name 2"
internal const val FIXTURE_ITEM_ITEM_NAME_3 = "A Item 0"
internal const val FIXTURE_ITEM_ITEM_NAME_4 = "Test Item Name 4"
internal const val FIXTURE_ITEM_ITEM_NAME_5 = "Test Item Name 5"
internal const val FIXTURE_ITEM_ITEM_NAME_6 = "Test Item Name 6"
internal const val FIXTURE_ITEM_ITEM_NAME_7 = "Test Item Name 7"
internal const val FIXTURE_ITEM_ITEM_NAME_8 = "Test Item Name 8"
internal const val FIXTURE_ITEM_ITEM_NAME_9 = "Test Item Name 9"
