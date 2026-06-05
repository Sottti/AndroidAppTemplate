package com.sottti.android.app.template.presentation.character.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.character.details.data.CharacterDetailsViewModel
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsActions
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.previews.PreviewAndroidAppTemplate

@Composable
public fun CharacterDetails(id: Int) {
    val viewModel = hiltViewModel<CharacterDetailsViewModel, CharacterDetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(CharacterId(id)) },
    )
    CharacterDetails(viewModel)
}

@Composable
internal fun CharacterDetails(
    viewModel: CharacterDetailsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterDetails(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CharacterDetails(
    state: CharacterDetailsState,
    onAction: (CharacterDetailsActions) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    CharacterDetailsContent(
        onAction = onAction,
        state = state,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
@PreviewAndroidAppTemplate
internal fun CharacterDetailsUiPreview(
    @PreviewParameter(CharacterDetailsUiStateProvider::class)
    state: CharacterDetailsState,
) {
    AndroidAppTemplateTheme {
        CharacterDetails(
            state = state,
            onAction = {},
        )
    }
}
