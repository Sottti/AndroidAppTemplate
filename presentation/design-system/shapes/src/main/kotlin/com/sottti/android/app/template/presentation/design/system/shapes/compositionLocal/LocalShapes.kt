package com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal

import androidx.compose.runtime.staticCompositionLocalOf
import com.sottti.android.app.template.presentation.design.system.shapes.model.Shapes

internal val LocalShapes = staticCompositionLocalOf<Shapes> {
    error("No shapes provided. Make sure to wrap your composables in a custom theme.")
}
