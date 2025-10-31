package com.sottti.android.app.template.presentation.item.details.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemDetailsActions {
    @Immutable
    data object NavigateBack : ItemDetailsActions
}
