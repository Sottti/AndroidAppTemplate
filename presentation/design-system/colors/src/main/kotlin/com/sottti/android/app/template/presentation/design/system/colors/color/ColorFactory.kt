package com.sottti.android.app.template.presentation.design.system.colors.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.darkColorScheme
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.dynamicColorScheme
import com.sottti.android.app.template.presentation.design.system.colors.color.mapper.lightColorScheme

@Composable
@ReadOnlyComposable
public fun colors(
    colorContrast: ColorContrast,
    useDarkTheme: Boolean,
    useDynamicColor: Boolean = false,
): ColorScheme = when {
    useDynamicColor -> dynamicColorScheme(useDarkTheme)
    useDarkTheme -> colorContrast.darkColorScheme()
    else -> colorContrast.lightColorScheme()
}
