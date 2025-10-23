package com.sottti.android.app.template.data.settings.managers

import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject
import android.app.UiModeManager as AndroidUiManager

internal class UiModeManagerImpl @Inject constructor(
    private val uiModeManager: AndroidUiManager?,
) : UiModeManager {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun getContrast(): Float? = uiModeManager?.contrast
}
