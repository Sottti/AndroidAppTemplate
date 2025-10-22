package com.sottti.android.app.template.presentation.design.system.dimensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun DimensionsLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalDimensions provides dimensions()) {
        content()
    }
}
