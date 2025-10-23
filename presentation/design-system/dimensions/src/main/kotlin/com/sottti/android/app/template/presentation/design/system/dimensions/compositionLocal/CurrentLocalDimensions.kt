package com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.dimensions.model.Dimensions

public val dimensions: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalDimensions.current
