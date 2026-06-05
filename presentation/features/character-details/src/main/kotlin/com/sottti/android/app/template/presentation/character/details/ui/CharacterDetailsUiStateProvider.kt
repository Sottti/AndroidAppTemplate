package com.sottti.android.app.template.presentation.character.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.presentation.character.details.data.initialState
import com.sottti.android.app.template.presentation.character.details.data.reduce
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState

internal class CharacterDetailsUiStateProvider : PreviewParameterProvider<CharacterDetailsState> {
    override val values: Sequence<CharacterDetailsState>
        get() = sequence {
            yield(loadingState)
            yield(errorState)
            yield(loadedState)
        }
}

private val topBarState = initialState.topBarState
internal val loadingState = CharacterDetailsState.Loading(topBarState)
internal val errorState = CharacterDetailsState.Error(topBarState)
internal val loadedState = loadingState.reduce(fixtureCharacter1)
