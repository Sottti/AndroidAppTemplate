package com.sottti.android.app.template.presentation.character.details.data

import com.sottti.android.app.template.presentation.design.system.icons.data.Icons
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.character.details.model.TopBarState

internal val initialState: CharacterDetailsState = CharacterDetailsState.Loading(
    topBarState = TopBarState(
        navigationIcon = Icons.Arrow.Back.filled,
        title = null,
    )
)
