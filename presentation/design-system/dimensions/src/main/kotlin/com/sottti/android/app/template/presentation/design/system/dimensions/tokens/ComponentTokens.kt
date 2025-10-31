package com.sottti.android.app.template.presentation.design.system.dimensions.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp
import com.sottti.android.app.template.presentation.design.system.dimensions.model.CardInGridDimensions
import com.sottti.android.app.template.presentation.design.system.dimensions.model.ComponentDimensions
import com.sottti.android.app.template.presentation.design.system.dimensions.model.ProgressIndicatorDimensions

@Composable
@ReadOnlyComposable
internal fun componentTokens() =
    ComponentDimensions(
        cardInGrid = CardInGridDimensions(
            small = 112.dp,
            medium = 128.dp,
            large = 144.dp,
        ),
        progressIndicator = ProgressIndicatorDimensions(
            small = 24.dp,
            medium = 36.dp,
            large = 48.dp,
        ),
    )
