package com.sottti.android.app.template.presentation.characters.list.ui

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.characters.list.data.CharactersListViewModel
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListState
import com.sottti.android.app.template.presentation.previews.PreviewAndroidAppTemplate

@Composable
public fun CharactersList() {
    CharactersList(hiltViewModel<CharactersListViewModel>())
}

@Composable
private fun CharactersList(
    viewModel: CharactersListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CharactersList(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CharactersList(
    state: CharactersListState,
    onAction: (CharactersListActions) -> Unit,
) {
    val lazyListState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    CharactersListContent(
        lazyListState = lazyListState,
        onAction = onAction,
        scrollBehavior = scrollBehavior,
        state = state,
    )
}

@Composable
@PreviewAndroidAppTemplate
internal fun CharactersListUiPreview(
    @PreviewParameter(CharactersListUiStateProvider::class)
    state: CharactersListState,
) {
    AndroidAppTemplateTheme {
        CharactersList(
            state = state,
            onAction = {},
        )
    }
}
