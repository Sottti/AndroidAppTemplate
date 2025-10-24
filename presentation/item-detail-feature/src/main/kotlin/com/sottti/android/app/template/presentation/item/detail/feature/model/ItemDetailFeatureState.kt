package com.sottti.android.app.template.presentation.item.detail.feature.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class ItemDetailFeatureState(
    @StringRes val buttonTextResId: Int,
    @StringRes val textResId: Int,
)
