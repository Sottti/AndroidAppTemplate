package com.sottti.android.app.template.presentation.design.system.shapes.tokens

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions

internal object ShapeTokens {

    val RoundedCornerExtraSmall: CornerBasedShape
        @Composable
        @ReadOnlyComposable
        get() = dimensions.cornerRadii.extraSmall

    val RoundedCornerSmall: CornerBasedShape
        @Composable
        @ReadOnlyComposable
        get() = dimensions.cornerRadii.small

    val RoundedCornerMedium: CornerBasedShape
        @Composable
        @ReadOnlyComposable
        get() = dimensions.cornerRadii.medium

    val RoundedCornerLarge: CornerBasedShape
        @Composable
        @ReadOnlyComposable
        get() = dimensions.cornerRadii.large

    val RoundedCornerExtraLarge: CornerBasedShape
        @Composable
        @ReadOnlyComposable
        get() = dimensions.cornerRadii.extraLarge
}
