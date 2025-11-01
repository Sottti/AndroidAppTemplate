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
        val manager = SystemColorContrastManagerFake(default = defaultContrast)

        val actual = manager.getSystemColorContrast()

        Truth.assertThat(actual).isEqualTo(defaultContrast)
    }

    @Test
    fun `when setting a new contrast, then the current contrast level is updated`() {
        val manager = SystemColorContrastManagerFake(default = SystemColorContrast.StandardContrast)
        val newContrast = SystemColorContrast.MediumContrast

        manager.setContrast(newContrast)
        val actual = manager.getSystemColorContrast()

        Truth.assertThat(actual).isEqualTo(newContrast)
    }

    @Test
    fun `when observing the system contrast, then it emits the default value and then new values`() =
        runTest {
            val defaultContrast = SystemColorContrast.StandardContrast
            val newContrast = SystemColorContrast.LowContrast
            val manager = SystemColorContrastManagerFake(default = defaultContrast)

            manager.observeSystemColorContrast().test {
                Truth.assertThat(awaitItem()).isEqualTo(defaultContrast)

                manager.setContrast(newContrast)

                Truth.assertThat(awaitItem()).isEqualTo(newContrast)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
