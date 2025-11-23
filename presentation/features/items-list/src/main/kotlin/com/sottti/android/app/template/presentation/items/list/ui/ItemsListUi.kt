package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.items.list.data.ItemsListViewModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import com.sottti.android.app.template.presentation.previews.PreviewAndroidAppTemplate

@Composable
public fun ItemList() {
    ItemList(hiltViewModel<ItemsListViewModel>())
}

@Composable
private fun ItemList(
    viewModel: ItemsListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ItemList(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ItemList(
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
@PreviewAndroidAppTemplate
internal fun ItemListUiPreview(
    @PreviewParameter(ItemsListUiStateProvider::class)
    state: ItemsListState,
) {
    AndroidAppTemplateTheme {
        ItemList(
            state = state,
            onAction = {},
        )
    }
}
