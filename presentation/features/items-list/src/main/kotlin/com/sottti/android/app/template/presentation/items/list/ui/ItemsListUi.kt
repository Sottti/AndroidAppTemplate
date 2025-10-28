package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
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
@OptIn(ExperimentalMaterial3Api::class)
private fun ItemListUi(
    state: ItemsListState,
    onAction: (ItemsListActions) -> Unit,
) {
    val lazyListState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    ItemsListContent(
        lazyListState = lazyListState,
        onAction = onAction,
        scrollBehavior = scrollBehavior,
        state = state,
    )
}


@Composable
@AndroidAppTemplatePreview
private fun ItemListUiPreview() {

}
