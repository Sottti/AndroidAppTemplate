package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.os.Build
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.UPSIDE_DOWN_CAKE])
internal class FakeUiModeManagerTest {

    private lateinit var uiModeManager: FakeUiModeManager

    @Before
    fun setUp() {
        uiModeManager = FakeUiModeManager()
    }

    @Test
    fun `given the fake is initialized, when getting contrast, then it should return the default null value`() {
        val contrast = uiModeManager.getContrast()

        assertThat(contrast).isNull()
    }

    @Test
    fun `given a contrast value is set, when getting contrast, then it should return the set value`() {
        val newContrast = 0.5f
        uiModeManager.setContrast(newContrast)

        val actualContrast = uiModeManager.getContrast()

        assertThat(actualContrast).isEqualTo(newContrast)
    }

    @Test
    fun `given a contrast value is set and then set to null, when getting contrast, then it should return null`() {
        uiModeManager.setContrast(0.8f)
        uiModeManager.setContrast(null)

        val actualContrast = uiModeManager.getContrast()

        assertThat(actualContrast).isNull()
    }
}
