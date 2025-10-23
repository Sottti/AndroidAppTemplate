package com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.shapes.model.Shapes

public val shapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
