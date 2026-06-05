package com.sottti.android.app.template.presentation.characters.list.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface CharactersListActions {
    @Immutable
    data class ShowCharacterDetail(val characterId: Int) : CharactersListActions
}
