package com.sottti.android.app.template.presentation.home.data

import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.presentation.feature.R
import com.sottti.android.app.template.presentation.home.model.FeatureState
import com.sottti.android.app.template.presentation.home.model.FeatureStateWrapper
import com.sottti.android.app.template.presentation.home.model.FeatureThemePropertiesState

internal fun initialState(
    systemColorContrast: SystemColorContrast,
    systemTheme: SystemTheme,
    dynamicColor: DynamicColor = DynamicColor(false),
) = FeatureStateWrapper(
    state = FeatureState(
        buttonTextResId = R.string.feature_button_text,
        textResId = R.string.feature_text,
    ),
    themeProperties = FeatureThemePropertiesState(
        dynamicColor = dynamicColor,
        systemColorContrast = systemColorContrast,
        systemTheme = systemTheme,
    ),
)
