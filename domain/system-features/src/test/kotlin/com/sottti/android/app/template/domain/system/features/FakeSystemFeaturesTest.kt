package com.sottti.android.app.template.domain.system.features

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class FakeSystemFeaturesTest {

    @Test
    fun `when created with default true, all features are available`() {
        val fake = FakeSystemFeatures(default = true)
        assertThat(fake.systemColorContrastAvailable()).isTrue()
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
        assertThat(fake.lightDarkSystemThemingAvailable()).isTrue()
    }

    @Test
    fun `when created with default false, all features are unavailable`() {
        val fake = FakeSystemFeatures(default = false)
        assertThat(fake.systemColorContrastAvailable()).isFalse()
        assertThat(fake.systemDynamicColorAvailable()).isFalse()
        assertThat(fake.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    fun `setSystemColorContrastAvailable correctly updates the contrast feature`() {
        val fake = FakeSystemFeatures(default = true)
        fake.setSystemColorContrastAvailable(false)
        assertThat(fake.systemColorContrastAvailable()).isFalse()
    }

    @Test
    fun `setSystemDynamicColorAvailable correctly updates the dynamic color feature`() {
        val fake = FakeSystemFeatures(default = false)
        fake.setSystemDynamicColorAvailable(true)
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
    }

    @Test
    fun `setLightDarkSystemThemingAvailable correctly updates the theming feature`() {
        val fake = FakeSystemFeatures(default = true)
        fake.setLightDarkSystemThemingAvailable(false)
        assertThat(fake.lightDarkSystemThemingAvailable()).isFalse()
    }
}
