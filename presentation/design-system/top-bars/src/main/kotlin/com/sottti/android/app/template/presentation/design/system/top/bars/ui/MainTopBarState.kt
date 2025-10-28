package com.sottti.android.app.template.presentation.design.system.top.bars.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Immutable

@Immutable
@OptIn(ExperimentalMaterial3Api::class)
internal data class MainTopBarState(
    val scrollBehavior: TopAppBarScrollBehavior? = null,
    val showTitle: Boolean = false,
    val titleResId: Int? = null,
)
