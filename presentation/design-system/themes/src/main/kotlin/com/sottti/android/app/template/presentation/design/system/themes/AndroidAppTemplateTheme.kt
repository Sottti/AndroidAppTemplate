package com.sottti.android.app.template.presentation.design.system.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorsLocalProvider
import com.sottti.android.app.template.presentation.design.system.colors.color.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.DimensionsLocalProvider
import com.sottti.android.app.template.presentation.design.system.shapes.ShapesLocalProvider
import com.sottti.android.app.template.presentation.design.system.typography.TypographyLocalProvider

@Composable
public fun AndroidAppTemplateTheme(
    dynamicColor: Boolean = true,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    themeVariant: AndroidAppTemplateThemeVariant = AndroidAppTemplateThemeVariant.Default,
    content: @Composable () -> Unit,
) {
    when (themeVariant) {
        AndroidAppTemplateThemeVariant.Default ->
            DefaultThemeVariant(
                dynamicColor = dynamicColor,
                isSystemInDarkTheme = isSystemInDarkTheme,
                content = content
            )
    }
}

@Composable
private fun DefaultThemeVariant(
    dynamicColor: Boolean,
    isSystemInDarkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(
        dynamicColor = dynamicColor,
        isSystemInDarkTheme = isSystemInDarkTheme,
    ) {
        TypographyLocalProvider {
            DimensionsLocalProvider {
                ShapesLocalProvider {
                    UpdateSystemBars(isSystemInDarkTheme)
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
    isSystemInDarkTheme: Boolean,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val isLightTheme = !isSystemInDarkTheme
            WindowCompat
                .getInsetsController(window, view)
                .apply {
                    isAppearanceLightStatusBars = isLightTheme
                    isAppearanceLightNavigationBars = isLightTheme
                }
        }
    }
}
