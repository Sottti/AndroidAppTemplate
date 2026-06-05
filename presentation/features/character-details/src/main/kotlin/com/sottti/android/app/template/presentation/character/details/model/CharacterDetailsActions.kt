package com.sottti.android.app.template.presentation.character.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface CharacterDetailsActions {
    @Immutable
    data object NavigateBack : CharacterDetailsActions

    @Immutable
    data object Retry : CharacterDetailsActions
}
