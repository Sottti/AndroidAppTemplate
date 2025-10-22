package com.sottti.android.app.template.presentation.design.system.colors.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun ColorsLocalProvider(
    dynamicColor: Boolean,
    isSystemInDarkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = colors(
        isSystemInDarkTheme = isSystemInDarkTheme,
        useDynamicColor = dynamicColor,
    )

    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
