package com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.android.app.template.domain.cores.models.DynamicColor
import com.sottti.android.app.template.domain.cores.models.SystemColorContrast
import com.sottti.android.app.template.domain.cores.models.SystemTheme
import com.sottti.android.app.template.presentation.design.system.colors.color.colors

@Composable
public fun ColorsLocalProvider(
    colorContrast: SystemColorContrast,
    dynamicColor: DynamicColor,
    systemTheme: SystemTheme,
    content: @Composable () -> Unit,
) {
    val colors = colors(
        colorContrast = colorContrast,
        dynamicColor = dynamicColor,
        systemTheme = systemTheme,
    )

    CompositionLocalProvider(LocalColors provides colors) {
        content()
    }
}
