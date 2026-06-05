package com.sottti.android.app.template.presentation.character.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState
import com.sottti.android.app.template.presentation.design.system.images.local.model.ImageState

@Immutable
internal sealed class CharacterDetailsState {
    abstract val topBarState: TopBarState

    @Immutable
    data class Loading(
        override val topBarState: TopBarState,
    ) : CharacterDetailsState()

    @Immutable
    data class Error(
        override val topBarState: TopBarState,
    ) : CharacterDetailsState()

    @Immutable
    data class Loaded(
        override val topBarState: TopBarState,
        val character: CharacterState,
    ) : CharacterDetailsState()
}

@Immutable
internal data class TopBarState(
    val navigationIcon: IconState,
    val title: String?,
)

@Immutable
internal sealed interface CharacterDetailsSectionState {
    @get:StringRes
    val header: Int
}

@Immutable
internal data class CharacterState(
    val image: CharacterImageState,
    val identity: CharacterIdentityState,
)

@Immutable
internal sealed interface CharacterImageState {
    @Immutable
    data class NetworkImage(
        val description: ImageContentDescription,
        val url: ImageUrl,
    ) : CharacterImageState

    @Immutable
    data class PlaceholderImage(
        val state: ImageState,
    ) : CharacterImageState
}

@Immutable
internal data class CharacterIdentityState(
    override val header: Int,
    val name: CharacterDetailsRow,
) : CharacterDetailsSectionState

@Immutable
internal data class CharacterDetailsRow(
    @StringRes val headline: Int,
    val trailing: String?,
)
