package com.sottti.android.app.template.data.settings.managers

import android.content.Context
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [34])
@RunWith(RobolectricTestRunner::class)
internal class ThemeManagerTest {

    private lateinit var initialContext: Context

    @Before
    fun setup() {
        initialContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `when the system is in dark mode, then getting the theme returns dark theme`() {
        val darkContext = getThemedContext(isNightMode = true)
        val darkManager = ThemeManagerImpl(context = darkContext)

        val theme = darkManager.getSystemTheme()

        assertThat(theme).isEqualTo(SystemTheme.DarkSystemTheme)
    }

    @Test
    fun `when the system is in light mode, then getting the theme returns light theme`() {
        val lightContext = getThemedContext(isNightMode = false)
        val lightManager = ThemeManagerImpl(context = lightContext)

        val theme = lightManager.getSystemTheme()

        assertThat(theme).isEqualTo(SystemTheme.LightSystemTheme)
    }

    @Test
    fun `when observing the system theme, then it emits the correct initial value`() =
        runTest {
            val lightContext = getThemedContext(isNightMode = false)
            val lightManager = ThemeManagerImpl(context = lightContext)

            lightManager.observeSystemTheme().test {
                assertThat(awaitItem()).isEqualTo(SystemTheme.LightSystemTheme)
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun getThemedContext(isNightMode: Boolean): Context {
        val newConfig = Configuration(initialContext.resources.configuration)

        val nightModeDelta = Configuration().apply {
            uiMode = when {
                isNightMode -> Configuration.UI_MODE_NIGHT_YES
                else -> Configuration.UI_MODE_NIGHT_NO
            }
        }

        newConfig.updateFrom(nightModeDelta)

        return initialContext.createConfigurationContext(newConfig)
    }
}
