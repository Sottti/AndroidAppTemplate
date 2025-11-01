package com.sottti.android.app.template.presentation.item.details.data

import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.presentation.item.details.R
import com.sottti.android.app.template.presentation.item.details.model.ImageState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsRow
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Error
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Loaded
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Loading
import com.sottti.android.app.template.presentation.item.details.model.ItemIdentityState
import com.sottti.android.app.template.presentation.item.details.model.ItemState

internal fun ItemDetailsState.reduce(item: Item): ItemDetailsState {
    val updatedTopBar = topBarState.copy(title = item.name.value)
    return when (this) {
        is Loaded -> copy(
            topBarState = updatedTopBar,
            item = item.toItemState(existing = this.item)
        )

        is Error, is Loading -> Loaded(
            topBarState = updatedTopBar,
            item = item.toItemState()
        )
    }
}

private fun Item.toItemState(
    existing: ItemState? = null,
): ItemState = existing?.copy(
    imageState = existing.imageState.copy(
        imageDescription = image.description,
        imageUrl = image.imageUrl
    ),
    identity = existing.identity.copy(
        name = existing.identity.name.copy(trailing = name.value),
        tagline = existing.identity.tagline.copy(trailing = tagline.value),
        year = existing.identity.year.copy(trailing = year.value.toString())
    )
)
    ?: ItemState(
        imageState = ImageState(
            imageDescription = image.description,
            imageUrl = image.imageUrl
        ),
        identity = ItemIdentityState(
            header = R.string.identity_name,
            name = ItemDetailsRow(
                headline = R.string.identity_name,
                trailing = name.value
            ),
            tagline = ItemDetailsRow(
                headline = R.string.identity_tagline,
                trailing = tagline.value
            ),
            year = ItemDetailsRow(
                headline = R.string.identity_year,
                trailing = year.value.toString()
            )
        )
    )
