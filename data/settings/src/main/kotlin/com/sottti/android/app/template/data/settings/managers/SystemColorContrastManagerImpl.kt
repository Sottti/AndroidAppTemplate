package com.sottti.android.app.template.data.settings.managers

import android.content.Context
import com.sottti.android.app.template.data.settings.mapper.toSystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import com.sottti.android.app.template.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SystemColorContrastManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val systemFeatures: SystemFeatures,
    private val uiModeManager: UiModeManager,
) : SystemColorContrastManager {
    override fun getSystemColorContrast(): SystemColorContrast =
        when {
            systemFeatures.systemColorContrastAvailable() -> {
                toSystemColorContrast(uiModeManager.getContrast())
            }

            else -> SystemColorContrast.StandardContrast
        }

    override fun observeSystemColorContrast(): Flow<SystemColorContrast> =
        context.observeConfigurationChanges({ getSystemColorContrast() })
}
