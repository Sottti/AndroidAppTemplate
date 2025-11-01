package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.presentation.item.details.data.initialState
import com.sottti.android.app.template.presentation.item.details.data.reduce
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState

internal class ItemsDetailsUiStateProvider : PreviewParameterProvider<ItemDetailsState> {
    override val values: Sequence<ItemDetailsState>
        get() = sequence {
            yield(loadingState)
            yield(errorState)
            yield(loadedState)
        }
}

private val topBarState = initialState.topBarState
private val loadingState = ItemDetailsState.Loading(topBarState)
private val errorState = ItemDetailsState.Error(topBarState)
private val loadedState = loadingState.reduce(fixtureItem1)
