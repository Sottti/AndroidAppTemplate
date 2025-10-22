package com.sottti.android.app.template.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun ShapesLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalShapes provides shapes()) {
        content()
    }
}
