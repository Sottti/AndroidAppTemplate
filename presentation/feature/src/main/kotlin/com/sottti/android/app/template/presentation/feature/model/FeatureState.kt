package com.sottti.android.app.template.presentation.feature.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class FeatureState(
    @StringRes val buttonTextResId: Int,
    @StringRes val textResId: Int,
)
