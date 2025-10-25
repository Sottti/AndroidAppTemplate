package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.items.list.data.ItemsListViewModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun ItemListUi() {
    ItemListUi(hiltViewModel<ItemsListViewModel>())
}

@Composable
private fun ItemListUi(
    viewModel: ItemsListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ItemListUi(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
private fun ItemListUi(
    state: ItemsListState,
    onAction: (ItemsListActions) -> Unit,
) {

}


@Composable
@AndroidAppTemplatePreview
private fun ItemListUiPreview() {

}
