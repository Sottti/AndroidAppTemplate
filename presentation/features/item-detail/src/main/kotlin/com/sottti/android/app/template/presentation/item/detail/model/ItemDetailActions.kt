package com.sottti.android.app.template.presentation.item.detail.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemDetailActions {
    @Immutable
    data object NavigateBack : ItemDetailActions
}
