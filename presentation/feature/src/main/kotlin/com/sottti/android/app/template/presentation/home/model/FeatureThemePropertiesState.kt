package com.sottti.android.app.template.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme

@Immutable
internal data class FeatureThemePropertiesState(
    val dynamicColor: DynamicColor,
    val systemColorContrast: SystemColorContrast,
    val systemTheme: SystemTheme,
)
