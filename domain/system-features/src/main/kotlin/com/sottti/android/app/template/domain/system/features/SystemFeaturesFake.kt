package com.sottti.android.app.template.domain.system.features

public class SystemFeaturesFake(
    default: Boolean,
) : SystemFeatures {

    private var isContrastAvailable = default
    private var isDynamicColorAvailable = default
    private var isLightDarkThemingAvailable = default

    override fun systemColorContrastAvailable(): Boolean = isContrastAvailable

    override fun systemDynamicColorAvailable(): Boolean = isDynamicColorAvailable

    override fun lightDarkSystemThemingAvailable(): Boolean = isLightDarkThemingAvailable

    public fun setSystemColorContrastAvailable(isAvailable: Boolean) {
        isContrastAvailable = isAvailable
    }

    public fun setSystemDynamicColorAvailable(isAvailable: Boolean) {
        isDynamicColorAvailable = isAvailable
    }

    public fun setLightDarkSystemThemingAvailable(isAvailable: Boolean) {
        isLightDarkThemingAvailable = isAvailable
    }
}
