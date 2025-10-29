package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.shapes
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBar
import com.sottti.android.app.template.presentation.images.network.NetworkImage
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import com.sottti.android.app.template.presentation.utils.plus

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
        Items(
            items = state.items.collectAsLazyPagingItems(),
            listState = lazyListState,
            nestedScrollConnection = scrollBehavior.nestedScrollConnection,
            onAction = onAction,
            padding = padding,
        )
    }
}

@Composable
private fun Items(
    items: LazyPagingItems<Item>,
    listState: LazyGridState,
    nestedScrollConnection: NestedScrollConnection,
    onAction: (ItemsListActions) -> Unit,
    padding: PaddingValues,
) {
    when (items.loadState.refresh) {
        is Loading -> FillMaxWidthProgressIndicator(padding)
        is LoadState.Error -> TODO()
        is NotLoading -> when (items.itemCount) {
            0 -> {}
            else -> ItemsLoaded(
                items = items,
                listState = listState,
                nestedScrollConnection = nestedScrollConnection,
                onAction = onAction,
                padding = padding,
            )
        }
    }
}

@Composable
private fun ItemsLoaded(
    items: LazyPagingItems<Item>,
    listState: LazyGridState,
    nestedScrollConnection: NestedScrollConnection,
    onAction: (ItemsListActions) -> Unit,
    padding: PaddingValues,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 112.dp),
        contentPadding = padding + PaddingValues(dimensions.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
    ) {
        items(
            count = items.itemCount,
            key = { index -> items[index]?.id?.value ?: index }
        ) { index -> items[index]?.let { item -> ItemCard(item = item, onAction = onAction) } }
    }
}

@Composable
private fun ItemCard(
    item: Item,
    onAction: (ItemsListActions) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        shape = shapes.roundedCorner.large,
        onClick = { onAction(ShowDetail) },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            item.image.let {
                NetworkImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                        .padding(dimensions.spacing.small),
                    url = it.imageUrl,
                    contentDescription = it.description,
                    roundedCorners = true,
                )
            }
            Text.Body.Small(
                modifier = Modifier
                    .padding(vertical = dimensions.spacing.smallMedium)
                    .padding(horizontal = dimensions.spacing.small),
                text = item.name.value,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun FillMaxWidthProgressIndicator(
    padding: PaddingValues = PaddingValues(vertical = dimensions.spacing.medium),
) {
    ProgressIndicator(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
    )
}
