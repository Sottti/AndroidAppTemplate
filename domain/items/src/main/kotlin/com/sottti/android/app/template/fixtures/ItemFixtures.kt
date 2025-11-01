package com.sottti.android.app.template.fixtures

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemDescription
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

public val fixtureItem1: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_1_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_1_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_1_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_1_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_1_ITEM_DESCRIPTION),
    )

public val fixtureItem2: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_2_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_2_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_2_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_2_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_2_ITEM_DESCRIPTION),
    )

public val fixtureItem3: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_3_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_3_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_3_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_3_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_3_ITEM_DESCRIPTION),
    )

public val fixtureItem4: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_4_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_4_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_4_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_4_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_4_ITEM_DESCRIPTION),
    )

public val fixtureItem5: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_5_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_5_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_5_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_5_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_5_ITEM_DESCRIPTION),
    )

public val fixtureItem6: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_6_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_6_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_6_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_6_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_6_ITEM_DESCRIPTION),
    )

public val fixtureItem7: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_7_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_7_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_7_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_7_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_7_ITEM_DESCRIPTION),
    )

public val fixtureItem8: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_8_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_8_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_8_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_8_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_8_ITEM_DESCRIPTION),
    )

public val fixtureItem9: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_9_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_9_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_9_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_9_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_9_ITEM_DESCRIPTION),
    )

public val fixtureItem10: Item =
    Item(
        id = ItemId(FIXTURE_ITEM_10_ITEM_ID),
        name = ItemName(FIXTURE_ITEM_10_ITEM_NAME),
        image = ItemImage(
            description = ImageContentDescription(FIXTURE_ITEM_10_IMAGE_DESCRIPTION),
            imageUrl = ImageUrl(FIXTURE_ITEM_10_IMAGE_URL),
        ),
        description = ItemDescription(FIXTURE_ITEM_10_ITEM_DESCRIPTION),
    )

public val listOfTwoItems: List<Item> = listOf(fixtureItem1, fixtureItem2)
public val listOfMultipleItems: List<Item> = listOf(
    fixtureItem1,
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


internal const val FIXTURE_ITEM_10_IMAGE_DESCRIPTION = "Description for test image 10"
internal const val FIXTURE_ITEM_10_IMAGE_URL = "http://example.com/image10.png"
internal const val FIXTURE_ITEM_10_ITEM_DESCRIPTION = "Test Item Description 10"
internal const val FIXTURE_ITEM_10_ITEM_ID = 10
internal const val FIXTURE_ITEM_10_ITEM_NAME = "Test Item Name 10"
internal const val FIXTURE_ITEM_1_IMAGE_DESCRIPTION = "Description for the test image 1"
internal const val FIXTURE_ITEM_1_IMAGE_URL = "http://example.com/image1.png"
internal const val FIXTURE_ITEM_1_ITEM_DESCRIPTION = "Test Item Description 1"
internal const val FIXTURE_ITEM_1_ITEM_ID = 1
internal const val FIXTURE_ITEM_1_ITEM_NAME = "Test Item Name 1"
internal const val FIXTURE_ITEM_2_IMAGE_DESCRIPTION = "Description for the test image 2"
internal const val FIXTURE_ITEM_2_IMAGE_URL = "http://example.com/image2.png"
internal const val FIXTURE_ITEM_2_ITEM_DESCRIPTION = "Test Item Description 2"
internal const val FIXTURE_ITEM_2_ITEM_ID = 2
internal const val FIXTURE_ITEM_2_ITEM_NAME = "Test Item Name 2"
internal const val FIXTURE_ITEM_3_IMAGE_DESCRIPTION = "Description for test image 3"
internal const val FIXTURE_ITEM_3_IMAGE_URL = "http://example.com/image3.png"
internal const val FIXTURE_ITEM_3_ITEM_DESCRIPTION = "Test Item Description 3"
internal const val FIXTURE_ITEM_3_ITEM_ID = 3
internal const val FIXTURE_ITEM_3_ITEM_NAME = "Test Item Name 3"
internal const val FIXTURE_ITEM_4_IMAGE_DESCRIPTION = "Description for test image 4"
internal const val FIXTURE_ITEM_4_IMAGE_URL = "http://example.com/image4.png"
internal const val FIXTURE_ITEM_4_ITEM_DESCRIPTION = "Test Item Description 4"
internal const val FIXTURE_ITEM_4_ITEM_ID = 4
internal const val FIXTURE_ITEM_4_ITEM_NAME = "A Item 4"
internal const val FIXTURE_ITEM_5_IMAGE_DESCRIPTION = "Description for test image 5"
internal const val FIXTURE_ITEM_5_IMAGE_URL = "http://example.com/image5.png"
internal const val FIXTURE_ITEM_5_ITEM_DESCRIPTION = "Test Item Description 5"
internal const val FIXTURE_ITEM_5_ITEM_ID = 5
internal const val FIXTURE_ITEM_5_ITEM_NAME = "Test Item Name 5"
internal const val FIXTURE_ITEM_6_IMAGE_DESCRIPTION = "Description for test image 6"
internal const val FIXTURE_ITEM_6_IMAGE_URL = "http://example.com/image6.png"
internal const val FIXTURE_ITEM_6_ITEM_DESCRIPTION = "Test Item Description 6"
internal const val FIXTURE_ITEM_6_ITEM_ID = 6
internal const val FIXTURE_ITEM_6_ITEM_NAME = "Test Item Name 6"
internal const val FIXTURE_ITEM_7_IMAGE_DESCRIPTION = "Description for test image 7"
internal const val FIXTURE_ITEM_7_IMAGE_URL = "http://example.com/image7.png"
internal const val FIXTURE_ITEM_7_ITEM_DESCRIPTION = "Test Item Description 7"
internal const val FIXTURE_ITEM_7_ITEM_ID = 7
internal const val FIXTURE_ITEM_7_ITEM_NAME = "Test Item Name 7"
internal const val FIXTURE_ITEM_8_IMAGE_DESCRIPTION = "Description for test image 8"
internal const val FIXTURE_ITEM_8_IMAGE_URL = "http://example.com/image8.png"
internal const val FIXTURE_ITEM_8_ITEM_DESCRIPTION = "Test Item Description 8"
internal const val FIXTURE_ITEM_8_ITEM_ID = 8
internal const val FIXTURE_ITEM_8_ITEM_NAME = "Test Item Name 8"
internal const val FIXTURE_ITEM_9_IMAGE_DESCRIPTION = "Description for test image 9"
internal const val FIXTURE_ITEM_9_IMAGE_URL = "http://example.com/image9.png"
internal const val FIXTURE_ITEM_9_ITEM_DESCRIPTION = "Test Item Description 9"
internal const val FIXTURE_ITEM_9_ITEM_ID = 9
internal const val FIXTURE_ITEM_9_ITEM_NAME = "Test Item Name 9"
