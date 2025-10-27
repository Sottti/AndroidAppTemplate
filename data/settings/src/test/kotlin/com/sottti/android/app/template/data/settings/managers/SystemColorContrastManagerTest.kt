package com.sottti.android.app.template.data.settings.managers

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.settings.managers.fakes.FakeUiModeManager
import com.sottti.android.app.template.data.settings.mapper.HIGH_CONTRAST_THRESHOLD
import com.sottti.android.app.template.data.settings.mapper.MEDIUM_CONTRAST_THRESHOLD
import com.sottti.android.app.template.data.settings.mapper.STANDARD_CONTRAST_THRESHOLD
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.system.features.FakeSystemFeatures
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
internal class SystemColorContrastManagerTest {

    private lateinit var context: Context
    private lateinit var fakeSystemFeatures: FakeSystemFeatures
    private lateinit var fakeUiModeManager: FakeUiModeManager
    private lateinit var manager: SystemColorContrastManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        fakeSystemFeatures = FakeSystemFeatures(default = true)

        fakeUiModeManager = FakeUiModeManager()
        manager = SystemColorContrastManagerImpl(
            context = context,
            systemFeatures = fakeSystemFeatures,
            uiModeManager = fakeUiModeManager
        )
    }

    @Test
    fun `when contrast feature is unavailable, then getting contrast returns standard`() {
        fakeSystemFeatures.setSystemColorContrastAvailable(false)

        val contrast = manager.getSystemColorContrast()

        assertThat(contrast).isEqualTo(SystemColorContrast.StandardContrast)
    }

    @Test
    fun `when contrast feature is available, then getting contrast returns medium`() {
        fakeSystemFeatures.setSystemColorContrastAvailable(true)

        fakeUiModeManager.setContrast(MEDIUM_CONTRAST_THRESHOLD)

        val contrast = manager.getSystemColorContrast()

        assertThat(contrast).isEqualTo(SystemColorContrast.MediumContrast)
    }

    @Test
    fun `when contrast feature is available and manager returns high, then getting contrast returns high`() {
        fakeSystemFeatures.setSystemColorContrastAvailable(true)

        fakeUiModeManager.setContrast(HIGH_CONTRAST_THRESHOLD)

        val contrast = manager.getSystemColorContrast()

        assertThat(contrast).isEqualTo(SystemColorContrast.HighContrast)
    }

    @Test
    fun `when observing contrast, then it emits the correct initial low contrast value`() =
        runTest {

            fakeSystemFeatures.setSystemColorContrastAvailable(true)

            fakeUiModeManager.setContrast(STANDARD_CONTRAST_THRESHOLD - 0.1f)

            manager.observeSystemColorContrast().test {
                assertThat(awaitItem()).isEqualTo(SystemColorContrast.LowContrast)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
