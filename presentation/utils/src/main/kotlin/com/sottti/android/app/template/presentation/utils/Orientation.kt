package com.sottti.android.app.template.presentation.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
public fun isPortraitOrientation(): Boolean =
    LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
