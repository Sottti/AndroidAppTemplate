package com.sottti.android.app.template.domain.system.features

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class SystemFeaturesTestFake {

    @Test
    fun `when created with default true, then all features are available`() {
        val systemFeatures = SystemFeaturesFake(default = true)

        assertThat(systemFeatures.systemColorContrastAvailable()).isTrue()
        assertThat(systemFeatures.systemDynamicColorAvailable()).isTrue()
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isTrue()
    }

    @Test
    fun `when created with default false, then all features are unavailable`() {
        val systemFeatures = SystemFeaturesFake(default = false)

        assertThat(systemFeatures.systemColorContrastAvailable()).isFalse()
        assertThat(systemFeatures.systemDynamicColorAvailable()).isFalse()
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    fun `when updating contrast availability, then only the contrast feature is updated`() {
        val systemFeatures = SystemFeaturesFake(default = true)

        systemFeatures.setSystemColorContrastAvailable(false)

        assertThat(systemFeatures.systemColorContrastAvailable()).isFalse()
        assertThat(systemFeatures.systemDynamicColorAvailable()).isTrue()
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isTrue()
    }

    @Test
    fun `when updating dynamic color availability, then only the dynamic color feature is updated`() {
        val systemFeatures = SystemFeaturesFake(default = false)

        systemFeatures.setSystemDynamicColorAvailable(true)

        assertThat(systemFeatures.systemColorContrastAvailable()).isFalse()
        assertThat(systemFeatures.systemDynamicColorAvailable()).isTrue()
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isFalse()
    }

    @Test
    fun `when updating theming availability, then only the theming feature is updated`() {
        val systemFeatures = SystemFeaturesFake(default = true)

        systemFeatures.setLightDarkSystemThemingAvailable(false)

        assertThat(systemFeatures.systemColorContrastAvailable()).isTrue()
        assertThat(systemFeatures.systemDynamicColorAvailable()).isTrue()
        assertThat(systemFeatures.lightDarkSystemThemingAvailable()).isFalse()
    }
}
