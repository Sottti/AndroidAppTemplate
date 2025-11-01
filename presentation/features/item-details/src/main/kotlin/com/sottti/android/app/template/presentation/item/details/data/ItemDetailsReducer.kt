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

internal fun ItemDetailsState.reduce(item: Item): ItemDetailsState =
    when (this) {
        is Loaded -> copy(
            topBarState = topBarState.copy(title = item.name.value),
            item = this.item.copy(
                imageState = this.item.imageState.copy(
                    imageDescription = item.image.description,
                    imageUrl = item.image.imageUrl,
                ),
                identity = this.item.identity.copy(
                    name = this.item.identity.name.copy(trailing = item.name.value),
                    description = this.item.identity.description.copy(trailing = item.description.value)
                ),
            ),
        )

        is Error,
        is Loading,
            -> Loaded(
            topBarState = topBarState.copy(title = item.name.value),
            item = ItemState(
                imageState = ImageState(
                    imageDescription = item.image.description,
                    imageUrl = item.image.imageUrl,
                ),
                identity = ItemIdentityState(
                    header = R.string.identity_name,
                    name = ItemDetailsRow(
                        headline = R.string.identity_name,
                        trailing = item.name.value,
                    ),
                    description = ItemDetailsRow(
                        headline = R.string.identity_description,
                        trailing = item.description.value,
                    )
                ),
            ),
        )
    }
