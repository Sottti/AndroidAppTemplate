package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.empty.EmptyUi
import com.sottti.android.app.template.presentation.design.system.error.ErrorButton
import com.sottti.android.app.template.presentation.design.system.error.ErrorUi
import com.sottti.android.app.template.presentation.design.system.images.local.Image
import com.sottti.android.app.template.presentation.design.system.images.network.NetworkImage
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.shapes
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBar
import com.sottti.android.app.template.presentation.items.list.R
import com.sottti.android.app.template.presentation.items.list.model.ItemImageState
import com.sottti.android.app.template.presentation.items.list.model.ItemImageState.NetworkImage
import com.sottti.android.app.template.presentation.items.list.model.ItemImageState.PlaceholderImage
import com.sottti.android.app.template.presentation.items.list.model.ItemState
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowItemDetail
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ItemsListContent(
    lazyListState: LazyGridState,
    onAction: (ItemsListActions) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: ItemsListState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar(titleResId = state.titleResId, scrollBehavior = scrollBehavior) },
    ) { padding ->
        val items = state.items.collectAsLazyPagingItems()
        PullToRefreshBox(
            isRefreshing = items.loadState.refresh is Loading,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = padding.calculateStartPadding(LocalLayoutDirection.current),
                    end = padding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .testTag(PULL_TO_REFRESH_TEST_TAG),
            onRefresh = { items.refresh() },
        ) {
            Items(
                bottomPadding = padding.calculateBottomPadding(),
                items = items,
                listState = lazyListState,
                onAction = onAction,
                scrollBehavior = scrollBehavior,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Items(
    bottomPadding: Dp,
    items: LazyPagingItems<ItemState>,
    listState: LazyGridState,
    onAction: (ItemsListActions) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val isInitialLoad = items.loadState.refresh is Loading && items.itemCount == 0
    val isError = items.loadState.refresh is Error && items.itemCount == 0
    val isListEmpty = items.loadState.refresh is NotLoading &&
        items.loadState.append.endOfPaginationReached &&
        items.itemCount == 0

    when {
        isInitialLoad -> ProgressIndicatorFillMaxSize(bottomPadding)
        isError -> ErrorUi(
            modifier = Modifier.padding(bottom = bottomPadding),
            button = ErrorButton { items.retry() },
        )

        isListEmpty -> EmptyUi(modifier = Modifier.padding(bottom = bottomPadding))
        else -> ItemsLoaded(
            items = items,
            listState = listState,
            onAction = onAction,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ItemsLoaded(
    items: LazyPagingItems<ItemState>,
    listState: LazyGridState,
    scrollBehavior: TopAppBarScrollBehavior,
    onAction: (ItemsListActions) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensions.components.cardInGrid.small),
        contentPadding = PaddingValues(dimensions.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
    ) {
        when (items.loadState.prepend) {
            is Error -> item(key = "prepend error", span = { GridItemSpan(maxLineSpan) }) {
                PaginationErrorGridItem(onRetry = { items.retry() })
            }

            is Loading -> item(key = "prepend loading") { ProgressIndicatorGridItem() }
            else -> Unit
        }

        items(
            count = items.itemCount,
            key = items.itemKey { it.id },
        ) { index -> items[index]?.let { item -> ItemCard(item = item, onAction = onAction) } }

        when (items.loadState.append) {
            is Error -> item(key = "append error", span = { GridItemSpan(maxLineSpan) }) {
                PaginationErrorGridItem(onRetry = { items.retry() })
            }

            is Loading -> item(key = "append loading") { ProgressIndicatorGridItem() }
            else -> Unit
        }
    }
}

@Composable
private fun LazyGridItemScope.ItemCard(
    item: ItemState,
    onAction: (ItemsListActions) -> Unit,
) {
    Card(
        modifier = Modifier
            .testTag(GRID_ITEM_TEST_TAG)
            .aspectRatio(1f)
            .animateItem(),
        shape = shapes.roundedCorner.large,
        onClick = { onAction(ShowItemDetail(item.id)) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardImage(state = item.image)
            CardText(text = item.name)
        }
    }
}

@Composable
private fun ColumnScope.CardImage(
    state: ItemImageState,
) {
    when (state) {
        is NetworkImage -> NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(dimensions.spacing.small),
            url = state.url,
            contentDescription = state.description,
            roundedCorners = true,
        )

        is PlaceholderImage -> Image(state = state.state)
    }
}

@Composable
private fun CardText(text: String) {
    Text.Body.Small(
        modifier = Modifier
            .padding(vertical = dimensions.spacing.smallMedium)
            .padding(horizontal = dimensions.spacing.small),
        text = text,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun ProgressIndicatorFillMaxSize(
    bottomPadding: Dp,
) {
    ProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPadding)
    )
}

@Composable
private fun ProgressIndicatorGridItem() {
    ProgressIndicator(
        modifier = Modifier
            .testTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .size(dimensions.components.cardInGrid.small)
    )
}

@Composable
private fun PaginationErrorGridItem(
    onRetry: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(PAGINATION_ERROR_TEST_TAG),
        shape = shapes.roundedCorner.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensions.spacing.small),
        ) {
            Text.Body.Medium(
                textResId = R.string.items_list_pagination_error,
                textAlign = TextAlign.Center,
            )
            Button(onClick = onRetry) {
                Text.Vanilla(R.string.items_list_pagination_error_retry)
            }
        }
    }
}
