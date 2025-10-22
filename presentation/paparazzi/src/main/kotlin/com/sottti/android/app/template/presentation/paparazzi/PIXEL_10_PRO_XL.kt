package com.sottti.android.app.template.presentation.paparazzi

import app.cash.paparazzi.DeviceConfig
import com.android.resources.Density
import com.android.resources.Keyboard
import com.android.resources.KeyboardState
import com.android.resources.LayoutDirection
import com.android.resources.Navigation
import com.android.resources.NightMode
import com.android.resources.ScreenOrientation
import com.android.resources.ScreenRatio
import com.android.resources.ScreenSize
import com.android.resources.TouchScreen
import com.android.resources.UiMode

internal val PIXEL_10_PRO_XL: DeviceConfig =
    DeviceConfig(
        screenHeight = 2992,
        screenWidth = 1344,
        xdpi = 486,
        ydpi = 486,
        orientation = ScreenOrientation.PORTRAIT,
        uiMode = UiMode.NORMAL,
        nightMode = NightMode.NOTNIGHT,
        density = Density.create(560),
        fontScale = 1f,
        layoutDirection = LayoutDirection.LTR,
        locale = null,
        ratio = ScreenRatio.LONG,
        size = ScreenSize.NORMAL,
        keyboard = Keyboard.NOKEY,
        touchScreen = TouchScreen.FINGER,
        keyboardState = KeyboardState.SOFT,
        softButtons = true,
        navigation = Navigation.NONAV,
        screenRound = null,
        released = "August 20, 2025"
    )
