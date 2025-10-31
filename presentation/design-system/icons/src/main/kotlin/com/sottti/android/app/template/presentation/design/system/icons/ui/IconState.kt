package com.sottti.android.app.template.presentation.design.system.icons.ui

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState

@Immutable
internal data class IconState(
    val crossfade: Boolean,
    val iconState: IconState,
    val onClick: (() -> Unit)? = null,
)
