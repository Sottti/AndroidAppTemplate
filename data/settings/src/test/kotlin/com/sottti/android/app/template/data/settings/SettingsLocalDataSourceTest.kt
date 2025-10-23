package com.sottti.android.app.template.data.settings

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.settings.datasource.SettingsLocalDataSource
import com.sottti.android.app.template.data.settings.managers.FakeSystemColorContrastManager
import com.sottti.android.app.template.data.settings.managers.FakeThemeManager
import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast.HighContrast
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast.MediumContrast
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast.StandardContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme.DarkSystemTheme
import com.sottti.android.app.template.domain.settings.model.SystemTheme.LightSystemTheme
import com.sottti.android.app.template.domain.system.features.FakeSystemFeatures
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class SettingsLocalDataSourceTest {

    private lateinit var fakeSystemFeatures: FakeSystemFeatures
    private lateinit var fakeSystemColorContrastManager: FakeSystemColorContrastManager
    private lateinit var fakeThemeManager: FakeThemeManager
    private lateinit var dataSource: SettingsLocalDataSource
    private var defaultSystemColorContrast = StandardContrast
    private var defaultSystemFeatures = true
    private var defaultTheme = LightSystemTheme

    @Before
    fun setUp() {
        fakeSystemFeatures = FakeSystemFeatures(default = defaultSystemFeatures)
        fakeSystemColorContrastManager = FakeSystemColorContrastManager(defaultSystemColorContrast)
        fakeThemeManager = FakeThemeManager(defaultTheme)

        dataSource = SettingsLocalDataSource(
            features = fakeSystemFeatures,
            systemColorContrastManager = fakeSystemColorContrastManager,
            themeManager = fakeThemeManager,
        )
    }

    @Test
    fun `when getting system color contrast, then the value from the manager is returned`() {
        val expectedContrast = HighContrast
        fakeSystemColorContrastManager.setContrast(expectedContrast)
        val actualContrast = dataSource.getSystemColorContrast()
        assertThat(actualContrast).isEqualTo(expectedContrast)
    }

    @Test
    fun `when observing system color contrast, then the flow from the manager is returned`() =
        runTest {
            dataSource.observeSystemColorContrast().test {
                assertThat(awaitItem()).isEqualTo(defaultSystemColorContrast)
                fakeSystemColorContrastManager.setContrast(MediumContrast)
                assertThat(awaitItem()).isEqualTo(MediumContrast)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when observing dynamic color, it emits the correct state and completes`() = runTest {
            fakeSystemFeatures.setSystemDynamicColorAvailable(true)

            dataSource.observeDynamicColor().test {
                assertThat(awaitItem()).isEqualTo(DynamicColor(true))
                awaitComplete()
            }

            fakeSystemFeatures.setSystemDynamicColorAvailable(false)
            dataSource.observeDynamicColor().test {
                assertThat(awaitItem()).isEqualTo(DynamicColor(false))
                awaitComplete()
            }
        }


    @Test
    fun `when observing system theme, then the flow from the manager is returned`() = runTest {
        dataSource.observeSystemTheme().test {
            assertThat(awaitItem()).isEqualTo(defaultTheme)
            fakeThemeManager.setTheme(DarkSystemTheme)
            assertThat(awaitItem()).isEqualTo(DarkSystemTheme)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when getting system theme, then the value from the manager is returned`() {
        val expectedTheme = DarkSystemTheme
        fakeThemeManager.setTheme(expectedTheme)
        val actualTheme = dataSource.getSystemTheme()
        assertThat(actualTheme).isEqualTo(expectedTheme)
    }
}
