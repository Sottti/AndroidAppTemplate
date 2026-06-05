package com.sottti.android.app.template.presentation.app.shell.data

import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.presentation.app.shell.model.AppShellState

internal fun AppShellState.reduce(
    dynamicColor: DynamicColor,
    systemColorContrast: SystemColorContrast,
    systemTheme: SystemTheme,
): AppShellState = copy(
    dynamicColor = dynamicColor,
    systemColorContrast = systemColorContrast,
    systemTheme = systemTheme,
)
