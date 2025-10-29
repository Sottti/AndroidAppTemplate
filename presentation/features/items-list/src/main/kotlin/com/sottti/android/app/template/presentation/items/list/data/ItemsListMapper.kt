package com.sottti.android.app.template.presentation.items.list.data

import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel


internal fun Item.toUi() = ItemUiModel(
    description = image.description,
    id = id.value,
    imageUrl = image.imageUrl,
    name = name.value,
)
