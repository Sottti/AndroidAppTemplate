package com.sottti.android.app.template.presentation.item.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.item.detail.data.ItemDetailViewModel
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailActions
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailState

@Composable
public fun ItemDetailUi() {
    ItemDetailUi(hiltViewModel<ItemDetailViewModel>())
}

@Composable
internal fun ItemDetailUi(
    viewModel: ItemDetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FeatureUi(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
internal fun FeatureUi(
    state: ItemDetailState,
    onAction: (ItemDetailActions) -> Unit,
) {

}
