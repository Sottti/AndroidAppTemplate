package com.sottti.android.app.template.data.settings.datasource.local.managers

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FakeThemeManagerTest {

    @Test
    fun `when created, then it holds the provided default theme`() {
        val defaultTheme = SystemTheme.DarkSystemTheme
        val fake = FakeThemeManager(default = defaultTheme)

        val actual = fake.getSystemTheme()

        Truth.assertThat(actual).isEqualTo(defaultTheme)
    }

    @Test
    fun `when setting a new theme, then the current theme is updated`() {
        val fake = FakeThemeManager(default = SystemTheme.DarkSystemTheme)
        val newTheme = SystemTheme.LightSystemTheme

        fake.setTheme(newTheme)
        val actual = fake.getSystemTheme()

        Truth.assertThat(actual).isEqualTo(newTheme)
    }

    @Test
    fun `when observing the system theme, then it emits the default value first and then new values`() =
        runTest {
            val defaultTheme = SystemTheme.LightSystemTheme
            val newTheme = SystemTheme.DarkSystemTheme
            val fake = FakeThemeManager(default = defaultTheme)

            fake.observeSystemTheme().test {
                Truth.assertThat(awaitItem()).isEqualTo(defaultTheme)

                fake.setTheme(newTheme)

                Truth.assertThat(awaitItem()).isEqualTo(newTheme)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
