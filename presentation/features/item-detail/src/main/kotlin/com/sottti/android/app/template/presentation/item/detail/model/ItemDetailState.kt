package com.sottti.android.app.template.presentation.item.detail.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemDetailState {
    @Immutable
    data object Loading : ItemDetailState

    @Immutable
    data object Idle : ItemDetailState

    @Immutable
    data class Loaded(
        @DrawableRes val imageResId: Int,
        @StringRes val titleResId: Int,
        @StringRes val subtitleResId: Int,
    ) : ItemDetailState
}
