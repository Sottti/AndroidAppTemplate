package com.sottti.android.app.template.presentation.design.system.colors.color.mapper

import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.HighContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.LowContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.MediumContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.StandardContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorSchemes

@Composable
@ReadOnlyComposable
@RequiresApi(api = 31)
internal fun dynamicColorScheme(
    systemTheme: SystemTheme,
): ColorScheme = when (systemTheme) {
    SystemTheme.DarkSystemTheme -> dynamicDarkColorScheme(LocalContext.current)
    SystemTheme.LightSystemTheme -> dynamicLightColorScheme(LocalContext.current)
}


internal fun SystemColorContrast.darkColorScheme(): ColorScheme =
    when (this) {
        HighContrast -> ColorSchemes.Dark.highContrast
        MediumContrast -> ColorSchemes.Dark.mediumContrast
        StandardContrast -> ColorSchemes.Dark.standardContrast
        LowContrast -> ColorSchemes.Dark.standardContrast
    }

internal fun SystemColorContrast.lightColorScheme(): ColorScheme =
    when (this) {
        HighContrast -> ColorSchemes.Light.highContrast
        MediumContrast -> ColorSchemes.Light.mediumContrast
        StandardContrast -> ColorSchemes.Light.standardContrast
        LowContrast -> ColorSchemes.Light.standardContrast
    }
