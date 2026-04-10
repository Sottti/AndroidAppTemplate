package com.sottti.android.app.template.presentation.item.details.data

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemImage
import com.sottti.android.app.template.domain.items.model.ItemName
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.item.details.R
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsRow
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Loaded
import com.sottti.android.app.template.presentation.item.details.model.ItemIdentityState
import com.sottti.android.app.template.presentation.item.details.model.ItemImageState
import com.sottti.android.app.template.presentation.item.details.model.ItemState

internal fun ItemDetailsState.reduce(
    update: Item,
): ItemDetailsState =
    Loaded(
        topBarState = topBarState.copy(title = update.name.value),
        item = update.toItemState(),
    )

private fun Item.toItemState(): ItemState =
    ItemState(
        image = image.toState(),
        identity = ItemIdentityState(
            header = R.string.identity_header,
            name = name.toIdentityRow(),
        )
    )

private fun ItemImage?.toState(): ItemImageState = when (this) {
    null -> ItemImageState.PlaceholderImage(Images.AvatarPlaceholder.state)
    else -> ItemImageState.NetworkImage(description = description, url = url)
}

private fun ItemName.toIdentityRow() =
    ItemDetailsRow(
        headline = R.string.identity_name,
        trailing = value,
    )
