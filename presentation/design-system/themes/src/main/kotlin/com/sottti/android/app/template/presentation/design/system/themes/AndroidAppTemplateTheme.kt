package com.sottti.android.app.template.presentation.design.system.themes

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.core.models.SystemTheme.DarkSystemTheme
import com.sottti.android.app.template.domain.core.models.SystemTheme.LightSystemTheme
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.ColorsLocalProvider
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.DimensionsLocalProvider
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.ShapesLocalProvider
import com.sottti.android.app.template.presentation.design.system.typography.compositionLocal.TypographyLocalProvider

@Composable
public fun AndroidAppTemplateTheme(
    colorContrast: SystemColorContrast = SystemColorContrast.StandardContrast,
    dynamicColor: DynamicColor = DynamicColor(false),
    systemTheme: SystemTheme = if (isSystemInDarkTheme()) DarkSystemTheme else LightSystemTheme,
    themeVariant: AndroidAppTemplateThemeVariant = AndroidAppTemplateThemeVariant.Default,
    content: @Composable () -> Unit,
) {
    when (themeVariant) {
        AndroidAppTemplateThemeVariant.Default ->
            DefaultThemeVariant(
                colorContrast = colorContrast,
                content = content,
                dynamicColor = dynamicColor,
                systemTheme = systemTheme,
            )
    }
}

@Composable
private fun DefaultThemeVariant(
    colorContrast: SystemColorContrast,
    dynamicColor: DynamicColor,
    systemTheme: SystemTheme,
    content: @Composable () -> Unit,
) {
    ColorsLocalProvider(
        colorContrast = colorContrast,
        dynamicColor = dynamicColor,
        systemTheme = systemTheme,
    ) {
        TypographyLocalProvider {
            DimensionsLocalProvider {
                ShapesLocalProvider {
                    UpdateSystemBars(systemTheme)
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
    systemTheme: SystemTheme,
) {
    val view = LocalView.current
    val context = view.context

    if (!view.isInEditMode && context is Activity) {
        SideEffect {
            val window = context.window
            val isLightTheme = systemTheme == LightSystemTheme
            WindowCompat
                .getInsetsController(window, view)
                .apply {
                    isAppearanceLightStatusBars = isLightTheme
                    isAppearanceLightNavigationBars = isLightTheme
                }
        }
    }
}
