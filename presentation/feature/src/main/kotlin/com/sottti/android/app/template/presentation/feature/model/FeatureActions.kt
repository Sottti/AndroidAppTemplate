package com.sottti.android.app.template.presentation.feature.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface FeatureActions {
    @Immutable
    data object NoOp : FeatureActions
}
