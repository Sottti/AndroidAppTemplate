package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.android.app.template.presentation.items.list.R
import com.sottti.android.app.template.presentation.items.list.fixtures.listOfMultipleItemsUiModels
import com.sottti.android.app.template.presentation.items.list.fixtures.listOfTwoItemsUiModels
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class ItemsListUiStateProvider : PreviewParameterProvider<ItemsListState> {
    override val values: Sequence<ItemsListState>
        get() = sequence {
            yield(loadingState)
            yield(loadedStatePrependLoading)
            yield(loadedStateAppendLoading)
            yield(loadedStateAppendPrependBothLoading)
            yield(loadedStateNoPagination)
            yield(loadedStateAppendEndReached)
            yield(loadedStatePrependEndReached)
            yield(loadedStateAppendPrependBothEndsReached)
            yield(loadedStateLoadsOfItems)
            yield(loadedStateLoadsOfItemsRefreshing)
            yield(emptyState)
            yield(errorState)
        }
}

internal val loadingState = itemsListState(
    refreshState = Loading,
    data = emptyList(),
)
internal val loadedStateAppendLoading = itemsListState(appendState = Loading)
internal val loadedStatePrependLoading = itemsListState(prependState = Loading)
internal val loadedStateAppendPrependBothLoading =
    itemsListState(appendState = Loading, prependState = Loading)
internal val loadedStateNoPagination = itemsListState()
internal val loadedStateAppendEndReached = itemsListState(
    appendState = NotLoading(endOfPaginationReached = true),
)
internal val loadedStatePrependEndReached = itemsListState(
    prependState = NotLoading(endOfPaginationReached = true)
)
internal val loadedStateAppendPrependBothEndsReached = itemsListState(
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true)
)

internal val loadedStateLoadsOfItems = itemsListState(
    appendState = NotLoading(endOfPaginationReached = true),
    data = listOfMultipleItemsUiModels,
)

internal val loadedStateLoadsOfItemsRefreshing = itemsListState(
    refreshState = Loading,
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = listOfMultipleItemsUiModels,
)

internal val emptyState = itemsListState(
    refreshState = NotLoading(endOfPaginationReached = true),
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = emptyList(),
)

internal val errorState = itemsListState(
    refreshState = LoadState.Error(kotlin.Exception()),
    data = emptyList(),
)

@OptIn(ExperimentalMaterial3Api::class)
private fun itemsListState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<ItemUiModel> = listOfTwoItemsUiModels,
) = ItemsListState(
    titleResId = R.string.items_list_title,
    items = itemsPagingDataFlow(
        appendState = appendState,
        data = data,
        prependState = prependState,
        refreshState = refreshState,
    ),
)

private fun itemsPagingDataFlow(
    refreshState: LoadState,
    appendState: LoadState,
    prependState: LoadState,
    data: List<ItemUiModel>,
): Flow<PagingData<ItemUiModel>> =
    MutableStateFlow(
        PagingData.from(
            data = data,
            sourceLoadStates = LoadStates(
                refresh = refreshState,
                prepend = prependState,
                append = appendState,
            )
        )
    )
