package com.sottti.android.app.template.data.settings.managers.fakes

import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.android.app.template.data.settings.managers.UiModeManager

internal class FakeUiModeManager : UiModeManager {

    private var contrastValue: Float? = null

    fun setContrast(contrast: Float?) {
        this.contrastValue = contrast
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun getContrast(): Float? {
        return contrastValue
    }
}
