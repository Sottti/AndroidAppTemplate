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
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.empty.EmptyUi
import com.sottti.android.app.template.presentation.design.system.error.ErrorButton
import com.sottti.android.app.template.presentation.design.system.error.ErrorUi
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.shapes
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBar
import com.sottti.android.app.template.presentation.images.network.NetworkImage
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel
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
    items: LazyPagingItems<ItemUiModel>,
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
    items: LazyPagingItems<ItemUiModel>,
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
        if (items.loadState.prepend is Loading) {
            item(key = "prepend loading") { ProgressIndicatorGridItem() }
        }

        items(
            count = items.itemCount,
            key = items.itemKey { it.id },
        ) { index -> items[index]?.let { item -> ItemCard(item = item, onAction = onAction) } }

        if (items.loadState.append is Loading) {
            item(key = "append loading") { ProgressIndicatorGridItem() }
        }
    }
}

@Composable
private fun LazyGridItemScope.ItemCard(
    item: ItemUiModel,
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
            CardImage(
                description = item.description,
                imageUrl = item.imageUrl,
            )
            CardText(text = item.name)
        }
    }
}

@Composable
private fun ColumnScope.CardImage(
    description: ImageContentDescription,
    imageUrl: ImageUrl,
) {
    NetworkImage(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(dimensions.spacing.small),
        url = imageUrl,
        contentDescription = description,
        roundedCorners = true,
    )
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
    ProgressIndicator(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = bottomPadding))
}

@Composable
private fun ProgressIndicatorGridItem() {
    ProgressIndicator(
        modifier = Modifier
            .testTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .size(dimensions.components.cardInGrid.small)
    )
}
