package com.sottti.android.app.template.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp
import com.sottti.android.app.template.presentation.design.system.dimensions.model.Spacing

@Composable
@ReadOnlyComposable
internal fun spacingTokens(): Spacing =
    Spacing(
        none = 0.dp,
        extraSmall = 2.dp,
        small = 4.dp,
        smallMedium = 8.dp,
        medium = 16.dp,
        mediumLarge = 24.dp,
        large = 32.dp,
        extraLarge = 64.dp,
    )
