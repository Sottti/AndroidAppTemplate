package com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.android.app.template.presentation.design.system.shapes.shapes

@Composable
public fun ShapesLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalShapes provides shapes()) {
        content()
    }
}
