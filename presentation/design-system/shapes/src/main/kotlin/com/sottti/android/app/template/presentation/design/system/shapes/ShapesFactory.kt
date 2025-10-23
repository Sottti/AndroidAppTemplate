package com.sottti.android.app.template.presentation.design.system.shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.shapes.model.RoundedCornerShapes
import com.sottti.android.app.template.presentation.design.system.shapes.model.Shapes
import com.sottti.android.app.template.presentation.design.system.shapes.tokens.ShapeTokens

@Composable
@ReadOnlyComposable
internal fun shapes(): Shapes =
    Shapes(
        roundedCorner = RoundedCornerShapes(
            extraSmall = ShapeTokens.RoundedCornerExtraSmall,
            small = ShapeTokens.RoundedCornerSmall,
            medium = ShapeTokens.RoundedCornerMedium,
            large = ShapeTokens.RoundedCornerLarge,
            extraLarge = ShapeTokens.RoundedCornerExtraLarge,
        ),
    )
