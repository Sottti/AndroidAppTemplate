package com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.android.app.template.presentation.design.system.dimensions.dimensions

@Composable
public fun DimensionsLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalDimensions provides dimensions()) {
        content()
    }
}
