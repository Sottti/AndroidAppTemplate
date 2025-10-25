package com.sottti.android.app.template.presentation.items.list.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemsListState {
    @Immutable
    data object Loading : ItemsListState

    @Immutable
    data object Idle : ItemsListState

    @Immutable
    data class Loaded(
        @DrawableRes val imageResId: Int,
        @StringRes val titleResId: Int,
        @StringRes val subtitleResId: Int,
    ) : ItemsListState
}
