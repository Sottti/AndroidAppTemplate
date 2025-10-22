package com.sottti.android.app.template.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

@Composable
@ReadOnlyComposable
public fun colors(
    useDynamicColor: Boolean,
    isSystemInDarkTheme: Boolean,
): ColorScheme = when {
    useDynamicColor && isSystemInDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
    useDynamicColor -> dynamicLightColorScheme(LocalContext.current)
    isSystemInDarkTheme -> ColorSchemes.Dark.standardContrast
    else -> ColorSchemes.Light.standardContrast
}
