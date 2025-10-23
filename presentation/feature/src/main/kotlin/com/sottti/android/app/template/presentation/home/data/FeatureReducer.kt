package com.sottti.android.app.template.presentation.home.data

import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.presentation.home.model.FeatureStateWrapper

internal fun FeatureStateWrapper.reduce(
    dynamicColor: DynamicColor,
    systemColorContrast: SystemColorContrast,
    systemTheme: SystemTheme,
): FeatureStateWrapper = copy(
    themeProperties = themeProperties.copy(
        dynamicColor = dynamicColor,
        systemColorContrast = systemColorContrast,
        systemTheme = systemTheme,
    ),
    state = state.copy(),
)
