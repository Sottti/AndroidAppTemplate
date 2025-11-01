package com.sottti.android.app.template.domain.settings.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveSystemColorContrastFakeTest {

    @Test
    fun `when created, then it provides a flow that emits the initial value`() = runTest {
        val initialValue = SystemColorContrast.StandardContrast
        val fake = ObserveSystemColorContrastFake(initialValue)

        fake().test {
            assertThat(awaitItem()).isEqualTo(initialValue)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when emit is called, then the flow emits the new value`() = runTest {
        val fake = ObserveSystemColorContrastFake(initialValue = SystemColorContrast.StandardContrast)
        val newValue = SystemColorContrast.HighContrast

        fake().test {
            awaitItem()

            fake.emit(newValue)

            assertThat(awaitItem()).isEqualTo(newValue)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
