package com.sottti.android.app.template.presentation.design.system.dimensions.tokens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.sottti.android.app.template.presentation.design.system.dimensions.model.CornerRadii

@Composable
@ReadOnlyComposable
internal fun cornerRadiiTokens(): CornerRadii =
    CornerRadii(
        extraSmall = MaterialTheme.shapes.extraSmall,
        small = MaterialTheme.shapes.small,
        medium = MaterialTheme.shapes.medium,
        large = MaterialTheme.shapes.large,
        extraLarge = MaterialTheme.shapes.extraLarge,
    )
