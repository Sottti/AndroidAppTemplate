package com.sottti.android.app.template.presentation.design.system.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorContrast
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.android.app.template.presentation.design.system.colors.color.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.android.app.template.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.android.app.template.presentation.design.system.typography.TypographyLocalProvider

@Composable
public fun AndroidAppTemplateTheme(
    colorContrast: ColorContrast = ColorContrast.Standard,
    themeVariant: AndroidAppTemplateThemeVariant = AndroidAppTemplateThemeVariant.Default,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    when (themeVariant) {
        AndroidAppTemplateThemeVariant.Default ->
            DefaultThemeVariant(
                colorContrast = colorContrast,
                content = content,
                useDarkTheme = useDarkTheme,
                useDynamicColor = useDynamicColor,
            )
    }
}

@Composable
private fun DefaultThemeVariant(
    colorContrast: ColorContrast,
    useDarkTheme: Boolean,
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(
        colorContrast = colorContrast,
        useDarkTheme = useDarkTheme,
        useDynamicColor = useDynamicColor,
    ) {
        TypographyLocalProvider {
            DimensionsLocalProvider {
                ShapesLocalProvider {
                    UpdateSystemBars(useDarkTheme)
                    MaterialTheme(
                        colorScheme = colors,
                        content = content,
                        typography = typography,
                    )
                }
            }
        }
    }
}

@Composable
private fun UpdateSystemBars(
    useDarkTheme: Boolean,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val isLightTheme = !useDarkTheme
            WindowCompat
                .getInsetsController(window, view)
                .apply {
                    isAppearanceLightStatusBars = isLightTheme
                    isAppearanceLightNavigationBars = isLightTheme
                }
        }
    }
}
