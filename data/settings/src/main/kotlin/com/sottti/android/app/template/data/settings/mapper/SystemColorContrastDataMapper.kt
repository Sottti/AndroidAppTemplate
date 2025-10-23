package com.sottti.android.app.template.data.settings.mapper

import androidx.annotation.FloatRange
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast

private const val STANDARD_CONTRAST_THRESHOLD = 0f
private const val MEDIUM_CONTRAST_THRESHOLD = 0.5f
private const val HIGH_CONTRAST_THRESHOLD = 1.0f

internal fun toSystemColorContrast(
    @FloatRange(from = -1.0, to = 1.0) contrast: Float?,
): SystemColorContrast =
    when {
        contrast < STANDARD_CONTRAST_THRESHOLD -> SystemColorContrast.LowContrast
        contrast < MEDIUM_CONTRAST_THRESHOLD -> SystemColorContrast.StandardContrast
        contrast < HIGH_CONTRAST_THRESHOLD -> SystemColorContrast.MediumContrast
        contrast >= HIGH_CONTRAST_THRESHOLD -> SystemColorContrast.HighContrast
        else -> SystemColorContrast.StandardContrast
    }
