package com.sottti.android.app.template.presentation.design.system.colors.color

import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.domain.cores.models.DynamicColor
import com.sottti.android.app.template.domain.cores.models.SystemColorContrast
import com.sottti.android.app.template.domain.cores.models.SystemTheme
import com.sottti.android.app.template.domain.cores.models.SystemTheme.*
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.darkColorScheme
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.dynamicColorScheme
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.lightColorScheme

@Composable
@ReadOnlyComposable
public fun colors(
    colorContrast: SystemColorContrast,
    dynamicColor: DynamicColor,
    systemTheme: SystemTheme,
): ColorScheme = when {
    dynamicColor.enabled -> dynamicColorScheme(systemTheme)
    systemTheme == DarkSystemTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}
