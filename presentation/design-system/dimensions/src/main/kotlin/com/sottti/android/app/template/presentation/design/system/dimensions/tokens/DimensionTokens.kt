package com.sottti.android.app.template.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.dimensions.model.Dimensions

@Composable
@ReadOnlyComposable
internal fun dimensions() =
    Dimensions(
        cornerRadii = cornerRadiiTokens(),
        spacing = spacingTokens(),
    )
