package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.content.Context
import android.content.res.Configuration
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.utils.lifecycle.observeConfigurationChanges
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ThemeManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ThemeManager {
    override fun getSystemTheme(): SystemTheme =
        when (Configuration.UI_MODE_NIGHT_YES) {
            (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ->
                SystemTheme.DarkSystemTheme

            else -> SystemTheme.LightSystemTheme
        }

    override fun observeSystemTheme(): Flow<SystemTheme> =
        context.observeConfigurationChanges { getSystemTheme() }
}
