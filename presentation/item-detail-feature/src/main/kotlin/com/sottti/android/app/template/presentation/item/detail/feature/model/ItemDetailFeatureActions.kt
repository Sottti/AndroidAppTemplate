package com.sottti.android.app.template.presentation.item.detail.feature.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemDetailFeatureActions {
    @Immutable
    data object NavigateBack : ItemDetailFeatureActions
}
