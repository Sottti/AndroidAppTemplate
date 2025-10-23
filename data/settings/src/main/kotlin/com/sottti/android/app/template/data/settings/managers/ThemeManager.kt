package com.sottti.android.app.template.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import com.sottti.android.app.template.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ThemeManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val features: SystemFeatures,
    private val uiModeManager: UiModeManager?,
) {
    fun getSystemTheme(): SystemTheme =
        when (Configuration.UI_MODE_NIGHT_YES) {
            (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ->
                SystemTheme.DarkSystemTheme

            else -> SystemTheme.LightSystemTheme
        }

    fun observeSystemTheme(): Flow<SystemTheme> =
        context.observeConfigurationChanges { getSystemTheme() }
}
