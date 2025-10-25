package com.sottti.android.app.template.presentation.design.system.dimensions.model

import androidx.compose.runtime.Immutable

@Immutable
public data class Dimensions(
    val component: ComponentDimensions,
    val cornerRadii: CornerRadii,
    val spacing: Spacing,
)
