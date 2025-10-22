package com.sottti.android.app.template.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

public val shapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
