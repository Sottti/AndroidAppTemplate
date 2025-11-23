package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.presentation.item.details.data.initialState
import com.sottti.android.app.template.presentation.item.details.data.reduce
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState

internal class ItemDetailsUiStateProvider : PreviewParameterProvider<ItemDetailsState> {
    override val values: Sequence<ItemDetailsState>
        get() = sequence {
            yield(loadingState)
            yield(errorState)
            yield(loadedState)
        }
}

private val topBarState = initialState.topBarState
internal val loadingState = ItemDetailsState.Loading(topBarState)
internal val errorState = ItemDetailsState.Error(topBarState)
internal val loadedState = loadingState.reduce(fixtureItem1)
