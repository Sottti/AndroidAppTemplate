package com.sottti.android.app.template.presentation.design.system.empty

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState

@Immutable
internal data class EmptyState(
    val illustration: IllustrationState,
    val primaryText: Int,
    val secondaryText: Int,
)
