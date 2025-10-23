package com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

public val colors: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current
