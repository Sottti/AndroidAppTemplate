package com.sottti.android.app.template.data.settings.mappers

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.settings.mapper.toSystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class SystemColorContrastDataMapperTest(
    private val contrast: Float?,
    private val expected: SystemColorContrast,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "when contrast is {0}, then expect {1}")
        fun data(): Collection<Array<Any?>> =
            listOf(
                // Null case
                arrayOf(null, SystemColorContrast.StandardContrast),

                // LowContrast cases
                arrayOf(-1.0f, SystemColorContrast.LowContrast),
                arrayOf(-0.5f, SystemColorContrast.LowContrast),
                arrayOf(-0.001f, SystemColorContrast.LowContrast),

                // StandardContrast cases
                arrayOf(0.0f, SystemColorContrast.StandardContrast),
                arrayOf(0.25f, SystemColorContrast.StandardContrast),
                arrayOf(0.499f, SystemColorContrast.StandardContrast),

                // MediumContrast cases
                arrayOf(0.5f, SystemColorContrast.MediumContrast),
                arrayOf(0.75f, SystemColorContrast.MediumContrast),
                arrayOf(0.999f, SystemColorContrast.MediumContrast),

                // HighContrast cases
                arrayOf(1.0f, SystemColorContrast.HighContrast),
                arrayOf(1.1f, SystemColorContrast.HighContrast),
            )
    }

    @Test
    fun `it maps the float value to the correct contrast level`() {
        val actual = toSystemColorContrast(contrast)
        assertThat(actual).isEqualTo(expected)
    }
}
