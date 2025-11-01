package com.sottti.android.app.template.domain.settings.usecase

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import org.junit.Test

internal class GetSystemColorContrastFakeTest {

    @Test
    fun `when created, then it returns the default StandardContrast`() {
        val fake = GetSystemColorContrastFake()

        val actual = fake()

        assertThat(actual).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun `when contrast is set, then it returns the new value`() {
        val fake = GetSystemColorContrastFake()
        val newContrast = SystemColorContrast.HighContrast

        fake.setContrast(newContrast)
        val actual = fake()

        assertThat(actual).isEqualTo(newContrast)
    }
}
