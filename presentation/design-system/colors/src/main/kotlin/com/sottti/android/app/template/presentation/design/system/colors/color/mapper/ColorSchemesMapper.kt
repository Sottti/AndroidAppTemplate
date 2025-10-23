package com.sottti.android.app.template.presentation.design.system.colors.color.mapper

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorContrast
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorSchemes

@Composable
@ReadOnlyComposable
@RequiresApi(api = 31)
internal fun dynamicColorScheme(
    useDarkTheme: Boolean,
): ColorScheme = when (useDarkTheme) {
    true -> dynamicDarkColorScheme(LocalContext.current)
    false -> dynamicLightColorScheme(LocalContext.current)
}


internal fun ColorContrast.darkColorScheme(): ColorScheme =
    when (this) {
        ColorContrast.High -> ColorSchemes.Dark.highContrast
        ColorContrast.Medium -> ColorSchemes.Dark.mediumContrast
        ColorContrast.Standard -> ColorSchemes.Dark.standardContrast
        ColorContrast.Low -> ColorSchemes.Dark.standardContrast
    }

internal fun ColorContrast.lightColorScheme(): ColorScheme =
    when (this) {
        ColorContrast.High -> ColorSchemes.Light.highContrast
        ColorContrast.Medium -> ColorSchemes.Light.mediumContrast
        ColorContrast.Standard -> ColorSchemes.Light.standardContrast
        ColorContrast.Low -> ColorSchemes.Light.standardContrast
    }
