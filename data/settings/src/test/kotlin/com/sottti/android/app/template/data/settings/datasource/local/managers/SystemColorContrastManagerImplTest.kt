package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.settings.datasource.local.mapper.HIGH_CONTRAST_THRESHOLD
import com.sottti.android.app.template.data.settings.datasource.local.mapper.MEDIUM_CONTRAST_THRESHOLD
import com.sottti.android.app.template.data.settings.datasource.local.mapper.STANDARD_CONTRAST_THRESHOLD
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.system.features.SystemFeaturesFake
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
internal class SystemColorContrastManagerImplTest {

    private lateinit var context: Context
    private lateinit var manager: SystemColorContrastManager
    private lateinit var shadowApplication: ShadowApplication
    private lateinit var systemFeatures: SystemFeaturesFake
    private lateinit var uiModeManager: UiModeManagerFake

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        shadowApplication = Shadows.shadowOf(context.applicationContext as Application)
        systemFeatures = SystemFeaturesFake(default = true)
        uiModeManager = UiModeManagerFake()
        manager = SystemColorContrastManagerImpl(
            context = context,
            systemFeatures = systemFeatures,
            uiModeManager = uiModeManager
        )
    }

    @Test
    fun `given contrast feature is unavailable, when getting contrast, then it should return standard`() {
        systemFeatures.setSystemColorContrastAvailable(false)
        val contrast = manager.getSystemColorContrast()
        assertThat(contrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun `given contrast feature is available, when manager returns medium, then getting contrast returns medium`() {
        systemFeatures.setSystemColorContrastAvailable(true)
        uiModeManager.setContrast(MEDIUM_CONTRAST_THRESHOLD)
        val contrast = manager.getSystemColorContrast()
        assertThat(contrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun `given contrast feature is available, when manager returns high, then getting contrast returns high`() {
        systemFeatures.setSystemColorContrastAvailable(true)
        uiModeManager.setContrast(HIGH_CONTRAST_THRESHOLD)
        val contrast = manager.getSystemColorContrast()
        assertThat(contrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun `given contrast feature is available, when manager returns low, then getting contrast returns low`() {
        systemFeatures.setSystemColorContrastAvailable(true)
        uiModeManager.setContrast(STANDARD_CONTRAST_THRESHOLD - 0.1f)
        val contrast = manager.getSystemColorContrast()
        assertThat(contrast).isEqualTo(SystemColorContrast.LowContrast)
    }

    @Test
    fun `when observing contrast, then it should emit the correct initial value`() = runTest {
        uiModeManager.setContrast(HIGH_CONTRAST_THRESHOLD)

        manager.observeSystemColorContrast().test {
            assertThat(awaitItem()).isEqualTo(SystemColorContrast.HighContrast)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given an active observer, when configuration changes, then it should emit the new contrast value`() =
        runTest {
            uiModeManager.setContrast(MEDIUM_CONTRAST_THRESHOLD)

            manager.observeSystemColorContrast().test {
                assertThat(awaitItem()).isEqualTo(SystemColorContrast.MediumContrast)

                uiModeManager.setContrast(HIGH_CONTRAST_THRESHOLD)
                val resources = context.resources
                val newConfig = Configuration(resources.configuration)
                newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
                (context.applicationContext as Application).onConfigurationChanged(newConfig)

                assertThat(awaitItem()).isEqualTo(SystemColorContrast.HighContrast)
            }
        }
}
