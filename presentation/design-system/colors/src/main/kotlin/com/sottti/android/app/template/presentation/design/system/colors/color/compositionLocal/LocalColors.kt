package com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalColors = staticCompositionLocalOf<ColorScheme> {
    error("No colors provided. Make sure to wrap your composables in a custom theme.")
}
