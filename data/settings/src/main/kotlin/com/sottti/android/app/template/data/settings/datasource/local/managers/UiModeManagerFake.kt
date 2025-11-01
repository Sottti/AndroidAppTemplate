package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.os.Build
import androidx.annotation.RequiresApi

internal class UiModeManagerFake : UiModeManager {

    private var contrastValue: Float? = null

    fun setContrast(contrast: Float?) {
        this.contrastValue = contrast
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun getContrast(): Float? {
        return contrastValue
    }
}
