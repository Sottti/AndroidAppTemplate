package com.sottti.android.app.template.presentation.design.system.typography.compositionLocal

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf

@Suppress("TopLevelPropertyNaming")
internal val LocalTypography = staticCompositionLocalOf<Typography> {
    error("No typography provided. Make sure to wrap your composables in a custom theme.")
}
