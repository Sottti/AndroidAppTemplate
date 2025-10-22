package com.sottti.android.app.template.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.shapes.corner.roundedCornerShapes

@Composable
@ReadOnlyComposable
internal fun shapes(): Shapes =
    Shapes(
        roundedCorner = roundedCornerShapes(),
    )
