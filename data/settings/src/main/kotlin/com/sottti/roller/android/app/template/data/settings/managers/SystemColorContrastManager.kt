package com.sottti.roller.android.app.template.data.settings.managers

import android.app.UiModeManager
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import com.sottti.roller.android.app.template.data.settings.mapper.toSystemColorContrast
import javax.inject.Inject

internal class SystemColorContrastManager @Inject constructor(
    private val features: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    val systemColorContrast: SystemColorContrast
        get() = when {
            features.systemColorContrastAvailable() -> {
                // uiModeManager.contrast returns 0f for standard, 0.5f for medium and 1f for high
                val contrast = uiModeManager?.contrast ?: 0f
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }
}
