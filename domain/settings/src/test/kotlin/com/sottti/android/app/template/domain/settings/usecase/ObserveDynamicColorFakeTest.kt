package com.sottti.android.app.template.domain.settings.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.DynamicColor
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ObserveDynamicColorFakeTest {

    @Test
    fun `when created, then it provides a flow that emits the initial value`() = runTest {
        val initialValue = DynamicColor(enabled = false)
        val fake = ObserveDynamicColorFake(initialValue)

        fake().test {
            assertThat(awaitItem()).isEqualTo(initialValue)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when emit is called, then the flow emits the new value`() = runTest {
        val fake = ObserveDynamicColorFake(initialValue = DynamicColor(enabled = false))
        val newValue = DynamicColor(enabled = true)

        fake().test {
            awaitItem()

            fake.emit(newValue)

            assertThat(awaitItem()).isEqualTo(newValue)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
