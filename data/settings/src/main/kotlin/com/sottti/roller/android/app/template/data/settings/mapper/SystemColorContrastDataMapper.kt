package com.sottti.roller.android.app.template.data.settings.mapper

import androidx.annotation.FloatRange
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast

// uiModeManager.contrast returns 0f for standard, 0.5f for medium and 1f for high
internal fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float,
): SystemColorContrast =
    when {
        contrast < 0f -> SystemColorContrast.LowContrast
        contrast < 0.5f -> SystemColorContrast.StandardContrast
        contrast < 1.0f -> SystemColorContrast.MediumContrast
        contrast >= 1.0f -> SystemColorContrast.HighContrast
        else -> SystemColorContrast.StandardContrast
    }
