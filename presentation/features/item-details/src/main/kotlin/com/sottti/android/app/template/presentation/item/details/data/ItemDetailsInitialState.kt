package com.sottti.android.app.template.presentation.item.details.data

import com.sottti.android.app.template.presentation.design.system.icons.data.Icons
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.item.details.model.TopBarState

internal val initialState: ItemDetailsState = ItemDetailsState.Loading(
    topBarState = TopBarState(
        navigationIcon = Icons.Arrow.Back.filled,
        title = null,
    )
)
