package com.sottti.android.app.template.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class FeatureStateWrapper(
    val state: FeatureState,
    val themeProperties: FeatureThemePropertiesState,
)

internal fun FeatureStateWrapper.unwrap() = this.state
