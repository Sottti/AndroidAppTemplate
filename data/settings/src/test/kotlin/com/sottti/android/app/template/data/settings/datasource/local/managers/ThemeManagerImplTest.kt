package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemTheme.DarkSystemTheme
import com.sottti.android.app.template.domain.core.models.SystemTheme.LightSystemTheme
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication

@Config(sdk = [34])
@RunWith(RobolectricTestRunner::class)
internal class ThemeManagerImplTest {

    private lateinit var context: Context
    private lateinit var shadowApplication: ShadowApplication

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        shadowApplication = Shadows.shadowOf(context.applicationContext as Application)
    }

    @Test
    fun `given a dark-themed context, when getting the theme, then it returns dark theme`() {
        val darkContext = context.getThemedContext(isNightMode = true)
        val darkManager = ThemeManagerImpl(context = darkContext)

        val theme = darkManager.getSystemTheme()

        assertThat(theme).isEqualTo(DarkSystemTheme)
    }

    @Test
    fun `given a light-themed context, when getting the theme, then it returns light theme`() {
        val lightContext = context.getThemedContext(isNightMode = false)
        val lightManager = ThemeManagerImpl(context = lightContext)

        val theme = lightManager.getSystemTheme()

        assertThat(theme).isEqualTo(LightSystemTheme)
    }

    @Test
    fun `when observing the system theme, then it emits the correct initial value`() = runTest {
        val lightContext = context.getThemedContext(isNightMode = false)
        val lightManager = ThemeManagerImpl(context = lightContext)

        lightManager.observeSystemTheme().test {
            assertThat(awaitItem()).isEqualTo(LightSystemTheme)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given an active observer, when configuration changes from light to dark, then it emits dark theme`() = runTest {
        val themedContext = context.getThemedContext(isNightMode = false)
        val manager = ThemeManagerImpl(context = themedContext)

        manager.observeSystemTheme().test {
            assertThat(awaitItem()).isEqualTo(LightSystemTheme)

            val newConfig = Configuration(themedContext.resources.configuration)
            newConfig.uiMode =
                (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or
                        Configuration.UI_MODE_NIGHT_YES

            val themedRes = themedContext.resources
            themedRes.updateConfiguration(newConfig, themedRes.displayMetrics)

            (themedContext.applicationContext as Application).onConfigurationChanged(newConfig)

            assertThat(awaitItem()).isEqualTo(DarkSystemTheme)
        }
    }
}
