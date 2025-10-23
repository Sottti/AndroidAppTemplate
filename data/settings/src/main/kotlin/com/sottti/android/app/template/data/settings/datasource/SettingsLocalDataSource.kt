package com.sottti.android.app.template.data.settings.datasource

import com.sottti.android.app.template.data.settings.managers.SystemColorContrastManager
import com.sottti.android.app.template.data.settings.managers.ThemeManager
import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val features: SystemFeatures,
    private val systemColorContrastManager: SystemColorContrastManager,
    private val themeManager: ThemeManager,
) {
    fun getSystemColorContrast(): SystemColorContrast =
        systemColorContrastManager.getSystemColorContrast()

    fun observeSystemColorContrast(): Flow<SystemColorContrast> =
        systemColorContrastManager.observeSystemColorContrast()

    fun observeDynamicColor(): Flow<DynamicColor> = flow {
        when {
            features.systemDynamicColorAvailable() -> DynamicColor(true)
            else -> DynamicColor(false)
        }.let { emit(it) }
    }

    fun observeSystemTheme(): Flow<SystemTheme> =
        themeManager.observeSystemTheme()

    fun getSystemTheme(): SystemTheme =
        themeManager.getSystemTheme()
}
