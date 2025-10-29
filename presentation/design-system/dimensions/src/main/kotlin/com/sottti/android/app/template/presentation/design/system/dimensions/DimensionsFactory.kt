package com.sottti.android.app.template.presentation.design.system.dimensions

import androidx.compose.runtime.Composable
import com.sottti.android.app.template.presentation.design.system.dimensions.model.Dimensions
import com.sottti.android.app.template.presentation.design.system.dimensions.tokens.componentTokens
import com.sottti.android.app.template.presentation.design.system.dimensions.tokens.cornerRadiiTokens
import com.sottti.android.app.template.presentation.design.system.dimensions.tokens.spacingTokens

@Composable
internal fun dimensions(): Dimensions = Dimensions(
    components = componentTokens(),
    cornerRadii = cornerRadiiTokens(),
    spacing = spacingTokens(),
)
