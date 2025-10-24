package com.sottti.android.app.template.presentation.pully.list.feature.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
internal data class PullyListFeatureState(
    @StringRes val buttonTextResId: Int,
    @StringRes val textResId: Int,
)
