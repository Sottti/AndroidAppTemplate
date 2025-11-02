package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState
import com.sottti.android.app.template.presentation.design.system.icons.ui.Icon
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBar
import com.sottti.android.app.template.presentation.item.details.model.TopBarState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(
    onBackNavigation: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: TopBarState,
) {
    MainTopBar(
        scrollBehavior = scrollBehavior,
        title = state.title,
        navigationIcon = { NavigationIcon(state.navigationIcon, onBackNavigation) },
    )
}

@Composable
private fun NavigationIcon(
    state: IconState,
    onBackNavigation: () -> Unit,
) {
    Icon(
        iconState = state,
        onClick = { onBackNavigation() },
    )
}
