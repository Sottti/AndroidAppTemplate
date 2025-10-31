package com.sottti.android.app.template.data.system.features

import android.os.Build.VERSION_CODES
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
internal class SystemFeaturesImplTest {

    private lateinit var systemFeatures: SystemFeaturesImpl

    @Before
    fun setUp() {
        systemFeatures = SystemFeaturesImpl()
    }

    @Test
    @Config(sdk = [VERSION_CODES.TIRAMISU]) // SDK 33 (Below 34)
    fun `given SDK is below 34, when checking for color contrast, then it should return false`() {
        assertThat(systemFeatures.systemColorContrastAvailable()).isFalse()
    }

    @Test
    @Config(sdk = [VERSION_CODES.UPSIDE_DOWN_CAKE]) // SDK 34 (At boundary)
    fun `given SDK is 34, when checking for color contrast, then it should return true`() {
        assertThat(systemFeatures.systemColorContrastAvailable()).isTrue()
    }

    @Test
    @Config(sdk = [VERSION_CODES.R]) // SDK 30 (Below 31)
    fun `given SDK is below 31, when checking for dynamic color, then it should return false`() {
        assertThat(systemFeatures.systemDynamicColorAvailable()).isFalse()
    }

    @Test
    @Config(sdk = [VERSION_CODES.S]) // SDK 31 (At boundary)
    fun `given SDK is 31, when checking for dynamic color, then it should return true`() {
        assertThat(systemFeatures.systemDynamicColorAvailable()).isTrue()
    }

    @Test
    @Config(sdk = [VERSION_CODES.P]) // SDK 28 (Below 29)
    fun `given SDK is below 29, when checking for light-dark theming, then it should return false`() {
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    @Config(sdk = [VERSION_CODES.Q]) // SDK 29 (At boundary)
    fun `given SDK is 29, when checking for light-dark theming, then it should return true`() {
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isTrue()
    }
}
