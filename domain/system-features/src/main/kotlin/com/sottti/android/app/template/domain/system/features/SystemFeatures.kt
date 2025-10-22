package com.sottti.android.app.template.domain.system.features

import androidx.annotation.ChecksSdkIntAtLeast

public interface SystemFeatures {
    @ChecksSdkIntAtLeast(api = 31)
    public fun systemDynamicColorAvailable(): Boolean

    @ChecksSdkIntAtLeast(api = 29)
    public fun lightDarkSystemThemingAvailable(): Boolean
}
