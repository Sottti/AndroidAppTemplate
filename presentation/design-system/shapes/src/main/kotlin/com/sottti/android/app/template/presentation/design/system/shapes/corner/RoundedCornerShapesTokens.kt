package com.sottti.android.app.template.presentation.design.system.shapes.corner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.dimensions.dimensions

@Composable
@ReadOnlyComposable
internal fun roundedCornerShapes(): RoundedCornerShapes = RoundedCornerShapes(
    extraSmall = dimensions.cornerRadii.extraSmall,
    small = dimensions.cornerRadii.small,
    medium = dimensions.cornerRadii.medium,
    large = dimensions.cornerRadii.large,
    extraLarge = dimensions.cornerRadii.extraLarge,
)
