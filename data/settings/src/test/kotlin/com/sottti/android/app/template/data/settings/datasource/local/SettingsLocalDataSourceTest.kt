package com.sottti.android.app.template.data.settings.datasource.local

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.sottti.android.app.template.data.settings.datasource.local.managers.SystemColorContrastManagerFake
import com.sottti.android.app.template.data.settings.datasource.local.managers.ThemeManagerFake
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.system.features.SystemFeaturesFake
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SettingsLocalDataSourceTest {

    private lateinit var systemFeatures: SystemFeaturesFake
    private lateinit var systemColorContrastManager: SystemColorContrastManagerFake
    private lateinit var themeManager: ThemeManagerFake
    private lateinit var dataSource: SettingsLocalDataSource
    private var defaultSystemColorContrast = SystemColorContrast.StandardContrast
    private var defaultSystemFeatures = true
    private var defaultTheme = SystemTheme.LightSystemTheme

    @Before
    fun setUp() {
        systemFeatures = SystemFeaturesFake(default = defaultSystemFeatures)
        systemColorContrastManager = SystemColorContrastManagerFake(defaultSystemColorContrast)
        themeManager = ThemeManagerFake(defaultTheme)

        dataSource = SettingsLocalDataSource(
            features = systemFeatures,
            systemColorContrastManager = systemColorContrastManager,
            themeManager = themeManager,
        )
    }

    @Test
    fun `when getting system color contrast, then the value from the manager is returned`() {
        val expectedContrast = SystemColorContrast.HighContrast
        systemColorContrastManager.setContrast(expectedContrast)
        val actualContrast = dataSource.getSystemColorContrast()
        Truth.assertThat(actualContrast).isEqualTo(expectedContrast)
    }

    @Test
    fun `when observing system color contrast, then the flow from the manager is returned`() =
        runTest {
            dataSource.observeSystemColorContrast().test {
                Truth.assertThat(awaitItem()).isEqualTo(defaultSystemColorContrast)
                systemColorContrastManager.setContrast(SystemColorContrast.MediumContrast)
                Truth.assertThat(awaitItem()).isEqualTo(SystemColorContrast.MediumContrast)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when observing dynamic color, it emits the correct state and completes`() = runTest {
        systemFeatures.setSystemDynamicColorAvailable(true)

        dataSource.observeDynamicColor().test {
            Truth.assertThat(awaitItem()).isEqualTo(DynamicColor(true))
            awaitComplete()
        }

        systemFeatures.setSystemDynamicColorAvailable(false)
        dataSource.observeDynamicColor().test {
            Truth.assertThat(awaitItem()).isEqualTo(DynamicColor(false))
            awaitComplete()
        }
    }


    @Test
    fun `when observing system theme, then the flow from the manager is returned`() = runTest {
        dataSource.observeSystemTheme().test {
            Truth.assertThat(awaitItem()).isEqualTo(defaultTheme)
            themeManager.setTheme(SystemTheme.DarkSystemTheme)
            Truth.assertThat(awaitItem()).isEqualTo(SystemTheme.DarkSystemTheme)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when getting system theme, then the value from the manager is returned`() {
        val expectedTheme = SystemTheme.DarkSystemTheme
        themeManager.setTheme(expectedTheme)
        val actualTheme = dataSource.getSystemTheme()
        Truth.assertThat(actualTheme).isEqualTo(expectedTheme)
    }
}
