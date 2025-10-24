package com.sottti.android.app.template.presentation.pully.list.feature.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface PullyListFeatureActions {
    @Immutable
    data object ShowDetail : PullyListFeatureActions
}
