package com.sottti.android.app.template.data.settings.managers.fakes

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.HighContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.LowContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.MediumContrast
import com.sottti.android.app.template.domain.core.models.SystemColorContrast.StandardContrast
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class FakeSystemColorContrastManagerTest {

    @Test
    fun `when created, then it holds the provided default contrast`() {
        val defaultContrast = HighContrast
        val fake = FakeSystemColorContrastManager(default = defaultContrast)

        val actual = fake.getSystemColorContrast()

        assertThat(actual).isEqualTo(defaultContrast)
    }

    @Test
    fun `when setting a new contrast, then the current contrast level is updated`() {
        val fake = FakeSystemColorContrastManager(default = StandardContrast)
        val newContrast = MediumContrast

        fake.setContrast(newContrast)
        val actual = fake.getSystemColorContrast()

        assertThat(actual).isEqualTo(newContrast)
    }

    @Test
    fun `when observing the system contrast, then it emits the default value and then new values`() =
        runTest {
            val defaultContrast = StandardContrast
            val newContrast = LowContrast
            val fake = FakeSystemColorContrastManager(default = defaultContrast)

            fake.observeSystemColorContrast().test {
                assertThat(awaitItem()).isEqualTo(defaultContrast)

                fake.setContrast(newContrast)

                assertThat(awaitItem()).isEqualTo(newContrast)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
