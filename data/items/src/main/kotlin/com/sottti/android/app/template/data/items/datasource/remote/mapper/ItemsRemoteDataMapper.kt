package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal fun List<ItemApiModel>.toDomain(): List<Item> =
    map { itemApiModel -> itemApiModel.toDomain() }

internal fun ItemApiModel.toDomain(): Item =
    Item(
        id = ItemId(checkedId(id, name)),
        name = ItemName(name),
        image = ItemImage(
            description = ImageContentDescription(description),
            imageUrl = createImageUrl(id),
        ),
    )

// https://github.com/alxiw/punkapi/issues/2
private fun checkedId(id: Int, name: String): Int =
    when {
        id < 331 -> id
        id == 331 && name == "Lime Zephyr V2 (Fanzine)" -> id
        id == 331 -> 332
        else -> id + 1
    }

private fun createImageUrl(id: Int) =
    ImageUrl("https://picsum.photos/id/$id/400")
