package com.sottti.android.app.template.presentation.items.list.data

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel

internal fun List<Item>.toUi() = map { item -> item.toUi() }

internal fun Item.toUi() = ItemUiModel(
    description = image.description,
    id = id.value,
    imageUrl = image.imageUrl,
    name = name.value,
)
