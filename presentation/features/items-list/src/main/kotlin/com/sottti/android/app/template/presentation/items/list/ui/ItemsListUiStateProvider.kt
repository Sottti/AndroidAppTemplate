package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.android.app.template.presentation.items.list.R
import com.sottti.android.app.template.presentation.items.list.data.fixtureItem2UiModel
import com.sottti.android.app.template.presentation.items.list.data.fixtureItemUiModel
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class ItemsListUiStateProvider : PreviewParameterProvider<ItemsListState> {
    override val values: Sequence<ItemsListState>
        get() = sequence {
            yield(loadingState)
            yield(loadedStateAppendLoading)
            yield(loadedStatePrependLoading)
            yield(loadedStateAppendPrependBothLoading)
            yield(loadedStateNoPagination)
            yield(loadedStateAppendEndReached)
            yield(loadedStatePrependEndReached)
            yield(loadedStateAppendPrependBothEndsReached)
            yield(emptyState)
            yield(errorState)
        }
}

private val loadingState = itemsListState(
    refreshState = Loading,
    data = emptyList(),
)
private val loadedStateAppendLoading = itemsListState(appendState = Loading)
private val loadedStatePrependLoading = itemsListState(prependState = Loading)
private val loadedStateAppendPrependBothLoading =
    itemsListState(appendState = Loading, prependState = Loading)
private val loadedStateNoPagination = itemsListState()
private val loadedStateAppendEndReached = itemsListState(
    appendState = NotLoading(endOfPaginationReached = true),
)
private val loadedStatePrependEndReached = itemsListState(
    prependState = NotLoading(endOfPaginationReached = true)
)
private val loadedStateAppendPrependBothEndsReached = itemsListState(
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true)
)

private val emptyState = itemsListState(
    refreshState = NotLoading(endOfPaginationReached = true),
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = emptyList(),
)

private val errorState = itemsListState(
    refreshState = LoadState.Error(kotlin.Exception()),
    data = emptyList(),
)

private val items = listOf(
    fixtureItemUiModel,
    fixtureItem2UiModel,
)

@OptIn(ExperimentalMaterial3Api::class)
private fun itemsListState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<ItemUiModel> = listOf(
        fixtureItemUiModel,
        fixtureItem2UiModel,
    ),
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
