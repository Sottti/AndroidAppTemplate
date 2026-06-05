package com.sottti.android.app.template.presentation.design.system.shapes.composition.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.shapes.model.Shapes

public val shapes: Shapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
