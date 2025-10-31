package com.sottti.android.app.template.presentation.item.details.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState

@Immutable
internal sealed class ItemDetailsState {
    abstract val topBarState: TopBarState


    @Immutable
    data class Loading(
        override val topBarState: TopBarState,
    ) : ItemDetailsState()

    @Immutable
    data class Error(
        override val topBarState: TopBarState,
    ) : ItemDetailsState()

    @Immutable
    data class Loaded(
        override val topBarState: TopBarState,
        val imageDescription : String,
        val imageUrl: ImageUrl,
        val name: String,
    ) : ItemDetailsState()
}

@Immutable
internal data class TopBarState(
    val navigationIcon: IconState,
    val title: String?,
)
