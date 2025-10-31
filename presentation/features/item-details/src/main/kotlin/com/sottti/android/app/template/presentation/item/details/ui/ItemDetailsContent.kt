package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ItemDetailsContent(
    state: ItemDetailsState,
    scrollBehavior: TopAppBarScrollBehavior,
    onAction: (ItemDetailsActions) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                onBackNavigation = { onAction(ItemDetailsActions.NavigateBack) },
                scrollBehavior = scrollBehavior,
                state = state.topBarState,
            )
        }
    ) { padding: PaddingValues ->
        when (state) {
            is ItemDetailsState.Loading -> ProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )

            is ItemDetailsState.Error -> {}
//                ErrorUi(
//                    modifier = Modifier.padding(padding),
//                    button = ErrorButton {}
//                )

            is ItemDetailsState.Loaded -> LoadedState(
                nestedScrollConnection = scrollBehavior.nestedScrollConnection,
                onAction = onAction,
                padding = padding,
                state = state,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoadedState(
    onAction: (ItemDetailsActions) -> Unit,
    padding: PaddingValues,
    nestedScrollConnection: NestedScrollConnection,
    state: ItemDetailsState.Loaded,
) {
    val topPadding = padding.calculateTopPadding() + dimensions.spacing.medium
    val bottomPadding = padding.calculateBottomPadding() + dimensions.spacing.medium

    LazyColumn(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = bottomPadding, top = topPadding),
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.mediumLarge),
    ) {

    }
}
