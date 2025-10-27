package com.sottti.android.app.template.presentation.home.data

import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.presentation.home.model.HomeState

internal fun HomeState.reduce(
    dynamicColor: DynamicColor,
    systemColorContrast: SystemColorContrast,
    systemTheme: SystemTheme,
): HomeState = copy(
    dynamicColor = dynamicColor,
    systemColorContrast = systemColorContrast,
    systemTheme = systemTheme,
)
