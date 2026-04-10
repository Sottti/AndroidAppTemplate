package com.sottti.android.app.template.presentation.items.list.data

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.items.list.model.ItemImageState.NetworkImage
import com.sottti.android.app.template.presentation.items.list.model.ItemImageState.PlaceholderImage
import com.sottti.android.app.template.presentation.items.list.model.ItemState

internal fun List<Item>.toUi() = map { item -> item.toUi() }

internal fun Item.toUi() = ItemState(
    id = id.value,
    name = name.value,
    image = image?.let {
        NetworkImage(description = it.description, url = it.url)
    } ?: PlaceholderImage(Images.AvatarPlaceholder.state))
