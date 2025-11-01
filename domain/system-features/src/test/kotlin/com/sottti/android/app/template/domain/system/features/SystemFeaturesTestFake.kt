package com.sottti.android.app.template.domain.system.features

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class SystemFeaturesTestFake {

    @Test
    fun `when created with default true, then all features are available`() {
        val fake = SystemFeaturesFake(default = true)

        assertThat(fake.systemColorContrastAvailable()).isTrue()
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
        assertThat(fake.lightDarkSystemThemingAvailable()).isTrue()
    }

    @Test
    fun `when created with default false, then all features are unavailable`() {
        val fake = SystemFeaturesFake(default = false)

        assertThat(fake.systemColorContrastAvailable()).isFalse()
        assertThat(fake.systemDynamicColorAvailable()).isFalse()
        assertThat(fake.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    fun `when updating contrast availability, then only the contrast feature is updated`() {
        val fake = SystemFeaturesFake(default = true)

        fake.setSystemColorContrastAvailable(false)

        assertThat(fake.systemColorContrastAvailable()).isFalse()
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
        assertThat(fake.lightDarkSystemThemingAvailable()).isTrue()
    }

    @Test
    fun `when updating dynamic color availability, then only the dynamic color feature is updated`() {
        val fake = SystemFeaturesFake(default = false)

        fake.setSystemDynamicColorAvailable(true)

        assertThat(fake.systemColorContrastAvailable()).isFalse()
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
        assertThat(fake.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    fun `when updating theming availability, then only the theming feature is updated`() {
        val fake = SystemFeaturesFake(default = true)

        fake.setLightDarkSystemThemingAvailable(false)

        assertThat(fake.systemColorContrastAvailable()).isTrue()
        assertThat(fake.systemDynamicColorAvailable()).isTrue()
        assertThat(fake.lightDarkSystemThemingAvailable()).isFalse()
    }
}
