package com.sottti.android.app.template.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import com.sottti.android.app.template.utils.lifecycle.observeConfigurationChanges
import com.sottti.android.app.template.data.settings.mapper.toSystemColorContrast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SystemColorContrastManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) : SystemColorContrastManager {
    override fun getSystemColorContrast(): SystemColorContrast =
        when {
            systemFeatures.systemColorContrastAvailable() -> {
                val contrast = uiModeManager?.contrast
                toSystemColorContrast(contrast)
            }

            else -> SystemColorContrast.StandardContrast
        }

    override fun observeSystemColorContrast(): Flow<SystemColorContrast> =
        context.observeConfigurationChanges({ getSystemColorContrast() })
}
