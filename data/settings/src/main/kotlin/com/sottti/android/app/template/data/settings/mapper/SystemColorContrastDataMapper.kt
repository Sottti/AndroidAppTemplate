package com.sottti.android.app.template.data.settings.mapper

import androidx.annotation.FloatRange
import androidx.annotation.VisibleForTesting
import com.sottti.android.app.template.domain.core.models.SystemColorContrast

@VisibleForTesting
internal const val STANDARD_CONTRAST_THRESHOLD = 0f

@VisibleForTesting
internal const val MEDIUM_CONTRAST_THRESHOLD = 0.5f

@VisibleForTesting
internal const val HIGH_CONTRAST_THRESHOLD = 1.0f

internal fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float?,
): SystemColorContrast {
    val nonNullContrast = contrast ?: return SystemColorContrast.StandardContrast
    return when {
        nonNullContrast < STANDARD_CONTRAST_THRESHOLD -> SystemColorContrast.LowContrast
        nonNullContrast < MEDIUM_CONTRAST_THRESHOLD -> SystemColorContrast.StandardContrast
        nonNullContrast < HIGH_CONTRAST_THRESHOLD -> SystemColorContrast.MediumContrast
        else -> SystemColorContrast.HighContrast
    }
}
