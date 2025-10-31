package com.sottti.android.app.template.utils.lifecycle

import android.app.Application
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveConfigurationChangesTest {

    private val app: Application = ApplicationProvider.getApplicationContext()

    @Test
    fun `emits initial value and then updates on configuration change`() = runTest {
        app.setOrientation(Configuration.ORIENTATION_PORTRAIT)

        val capturingContext = CapturingContext(app)

        capturingContext.observeConfigurationChanges(app::currentOrientationLabel).test {
            assertThat(awaitItem()).isEqualTo(PORTRAIT)

            val landscape = app.setOrientation(Configuration.ORIENTATION_LANDSCAPE)
            capturingContext.callbacks?.onConfigurationChanged(landscape)

            assertThat(awaitItem()).isEqualTo(LANDSCAPE)

            capturingContext.callbacks?.onConfigurationChanged(landscape)
            expectNoEvents()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `conflates rapid consecutive changes and delivers the latest one`() = runTest {
        app.setOrientation(Configuration.ORIENTATION_PORTRAIT)

        val capturingContext = CapturingContext(app)

        capturingContext.observeConfigurationChanges(app::currentOrientationLabel).test {
            assertThat(awaitItem()).isEqualTo(PORTRAIT)

            val land = app.setOrientation(Configuration.ORIENTATION_LANDSCAPE)
            capturingContext.callbacks?.onConfigurationChanged(land)

            val port = app.setOrientation(Configuration.ORIENTATION_PORTRAIT)
            capturingContext.callbacks?.onConfigurationChanged(port)

            val landAgain = app.setOrientation(Configuration.ORIENTATION_LANDSCAPE)
            capturingContext.callbacks?.onConfigurationChanged(landAgain)

            assertThat(awaitItem()).isEqualTo(LANDSCAPE)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
