package com.sottti.roller.android.app.template.data.settings.managers

import android.app.UiModeManager
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
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

    fun observeSystemTheme(): Flow<SystemTheme> = callbackFlow {
        trySend(getSystemTheme())

        val callbacks = object : ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) {
                trySend(getSystemTheme())
            }

            @Deprecated("Deprecated in Java")
            override fun onLowMemory() = Unit
        }

        context.registerComponentCallbacks(callbacks)
        awaitClose { context.unregisterComponentCallbacks(callbacks) }
    }
        .distinctUntilChanged()
        .conflate()
}
