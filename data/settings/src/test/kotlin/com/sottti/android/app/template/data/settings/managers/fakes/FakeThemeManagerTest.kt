package com.sottti.android.app.template.data.settings.managers.fakes

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.settings.model.SystemTheme.DarkSystemTheme
import com.sottti.android.app.template.domain.settings.model.SystemTheme.LightSystemTheme
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FakeThemeManagerTest {

    @Test
    fun `when created, then it holds the provided default theme`() {
        val defaultTheme = DarkSystemTheme
        val fake = FakeThemeManager(default = defaultTheme)

        val actual = fake.getSystemTheme()

        assertThat(actual).isEqualTo(defaultTheme)
    }

    @Test
    fun `when setting a new theme, then the current theme is updated`() {
        val fake = FakeThemeManager(default = DarkSystemTheme)
        val newTheme = LightSystemTheme

        fake.setTheme(newTheme)
        val actual = fake.getSystemTheme()

        assertThat(actual).isEqualTo(newTheme)
    }

    @Test
    fun `when observing the system theme, then it emits the default value first and then new values`() =
        runTest {
            val defaultTheme = LightSystemTheme
            val newTheme = DarkSystemTheme
            val fake = FakeThemeManager(default = defaultTheme)

            fake.observeSystemTheme().test {
                assertThat(awaitItem()).isEqualTo(defaultTheme)

                fake.setTheme(newTheme)

                assertThat(awaitItem()).isEqualTo(newTheme)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
