package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.os.Build
import androidx.annotation.RequiresApi
import com.sottti.android.app.template.data.settings.datasource.local.model.AndroidUiModeManager
import javax.inject.Inject

internal class UiModeManagerImpl @Inject constructor(
    private val uiModeManager: AndroidUiModeManager?,
) : UiModeManager {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun getContrast(): Float? = uiModeManager?.contrast
}
