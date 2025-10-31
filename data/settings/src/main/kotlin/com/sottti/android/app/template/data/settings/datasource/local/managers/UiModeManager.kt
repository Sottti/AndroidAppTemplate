package com.sottti.android.app.template.data.settings.datasource.local.managers

import androidx.annotation.FloatRange

internal fun interface UiModeManager {
    @FloatRange(from = -1.0, to = 1.0)
    fun getContrast(): Float?
}
