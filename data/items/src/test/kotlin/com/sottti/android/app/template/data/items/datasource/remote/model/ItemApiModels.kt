package com.sottti.android.app.template.data.items.datasource.remote.model

import com.sottti.android.app.template.data.items.datasource.data.item
import com.sottti.android.app.template.data.items.datasource.data.item2

internal val itemApiModel = ItemApiModel(
    id = item.id.value,
    name = item.name.value,
    imageUrl = item.image.imageUrl.value,
    imageDescription = item.image.description.value,
)

internal val item2ApiModel = ItemApiModel(
    id = item2.id.value,
    name = item2.name.value,
    imageUrl = item2.image.imageUrl.value,
    imageDescription = item2.image.description.value,
)
