package com.sottti.android.app.template.data.settings.datasource.local.managers

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class SystemColorContrastManagerTestFake {

    @Test
    fun `when created, then it holds the provided default contrast`() {
        val defaultContrast = SystemColorContrast.HighContrast
        val fake = SystemColorContrastManagerFake(default = defaultContrast)

        val actual = fake.getSystemColorContrast()

        Truth.assertThat(actual).isEqualTo(defaultContrast)
    }

    @Test
    fun `when setting a new contrast, then the current contrast level is updated`() {
        val fake = SystemColorContrastManagerFake(default = SystemColorContrast.StandardContrast)
        val newContrast = SystemColorContrast.MediumContrast

        fake.setContrast(newContrast)
        val actual = fake.getSystemColorContrast()

        Truth.assertThat(actual).isEqualTo(newContrast)
    }

    @Test
    fun `when observing the system contrast, then it emits the default value and then new values`() =
        runTest {
            val defaultContrast = SystemColorContrast.StandardContrast
            val newContrast = SystemColorContrast.LowContrast
            val fake = SystemColorContrastManagerFake(default = defaultContrast)

            fake.observeSystemColorContrast().test {
                Truth.assertThat(awaitItem()).isEqualTo(defaultContrast)

                fake.setContrast(newContrast)

                Truth.assertThat(awaitItem()).isEqualTo(newContrast)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
