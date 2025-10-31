package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.presentation.item.details.data.ItemDetailsViewModel
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState

@Composable
public fun ItemDetails(id: Int) {
    val viewModel = hiltViewModel<ItemDetailsViewModel, ItemDetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(ItemId(id)) },
    )
    ItemDetails(viewModel)
}

@Composable
internal fun ItemDetails(
    viewModel: ItemDetailsViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ItemDetails(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ItemDetails(
    state: ItemDetailsState,
    onAction: (ItemDetailsActions) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    ItemDetailsContent(
        onAction = onAction,
        state = state,
        scrollBehavior = scrollBehavior,
    )
}
