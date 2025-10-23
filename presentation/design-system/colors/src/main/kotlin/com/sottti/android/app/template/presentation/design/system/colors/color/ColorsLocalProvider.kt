package com.sottti.android.app.template.presentation.design.system.colors.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun ColorsLocalProvider(
    colorContrast: ColorContrast,
    useDarkTheme: Boolean,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colors = colors(
        colorContrast = colorContrast,
        useDarkTheme = useDarkTheme,
        useDynamicColor = useDynamicColor,
    )

    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
