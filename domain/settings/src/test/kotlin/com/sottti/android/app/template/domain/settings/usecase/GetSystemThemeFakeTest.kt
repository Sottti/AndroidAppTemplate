package com.sottti.android.app.template.domain.settings.usecase

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemTheme
import org.junit.Test

internal class GetSystemThemeFakeTest {

    @Test
    fun `when created, then it returns the default LightSystemTheme`() {
        val fake = GetSystemThemeFake()

        val actual = fake()

        assertThat(actual).isEqualTo(SystemTheme.LightSystemTheme)
    }

    @Test
    fun `when theme is set, then it returns the new value`() {
        val fake = GetSystemThemeFake()
        val newTheme = SystemTheme.DarkSystemTheme

        fake.setTheme(newTheme)
        val actual = fake()

        assertThat(actual).isEqualTo(newTheme)
    }
}
