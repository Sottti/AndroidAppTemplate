package com.sottti.android.app.template.data.system.features

import android.os.Build
import android.os.Build.VERSION_CODES.Q
import android.os.Build.VERSION_CODES.S
import android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE
import androidx.annotation.ChecksSdkIntAtLeast
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import javax.inject.Inject

internal class SystemFeaturesImpl @Inject constructor() : SystemFeatures {

    @ChecksSdkIntAtLeast(api = S)
    override fun systemDynamicColorAvailable(): Boolean = isAtLeastSdk31()

    @ChecksSdkIntAtLeast(api = Q)
    override fun lightDarkSystemThemingAvailable(): Boolean = isAtLeastSdk29()
}

@ChecksSdkIntAtLeast(api = UPSIDE_DOWN_CAKE)
private fun isAtLeastSdk34(): Boolean = Build.VERSION.SDK_INT >= UPSIDE_DOWN_CAKE

@ChecksSdkIntAtLeast(api = S)
private fun isAtLeastSdk31(): Boolean = Build.VERSION.SDK_INT >= S

@ChecksSdkIntAtLeast(api = Q)
private fun isAtLeastSdk29(): Boolean = Build.VERSION.SDK_INT >= Q
