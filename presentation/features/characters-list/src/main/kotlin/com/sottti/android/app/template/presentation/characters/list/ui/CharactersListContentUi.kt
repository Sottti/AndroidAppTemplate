package com.sottti.android.app.template.presentation.characters.list.ui

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
import com.sottti.android.app.template.presentation.design.system.dimensions.composition.local.dimensions
import com.sottti.android.app.template.presentation.design.system.empty.EmptyUi
import com.sottti.android.app.template.presentation.design.system.error.ErrorButton
import com.sottti.android.app.template.presentation.design.system.error.ErrorUi
import com.sottti.android.app.template.presentation.design.system.images.local.Image
import com.sottti.android.app.template.presentation.design.system.images.network.NetworkImage
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.shapes.composition.local.shapes
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBar
import com.sottti.android.app.template.presentation.characters.list.R
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState.NetworkImage
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState.PlaceholderImage
import com.sottti.android.app.template.presentation.characters.list.model.CharacterState
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions.ShowCharacterDetail
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CharactersListContent(
    lazyListState: LazyGridState,
    onAction: (CharactersListActions) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    state: CharactersListState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar(titleResId = state.titleResId, scrollBehavior = scrollBehavior) },
    ) { padding ->
        val characters = state.characters.collectAsLazyPagingItems()
        PullToRefreshBox(
            isRefreshing = characters.loadState.refresh is Loading,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = padding.calculateStartPadding(LocalLayoutDirection.current),
                    end = padding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .testTag(PULL_TO_REFRESH_TEST_TAG),
            onRefresh = { characters.refresh() },
        ) {
            Characters(
                bottomPadding = padding.calculateBottomPadding(),
                characters = characters,
                listState = lazyListState,
                onAction = onAction,
                scrollBehavior = scrollBehavior,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Characters(
    bottomPadding: Dp,
    characters: LazyPagingItems<CharacterState>,
    listState: LazyGridState,
    onAction: (CharactersListActions) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    val isInitialLoad = characters.loadState.refresh is Loading && characters.itemCount == 0
    val isError = characters.loadState.refresh is Error && characters.itemCount == 0
    val isListEmpty = characters.loadState.refresh is NotLoading &&
        characters.loadState.append.endOfPaginationReached &&
        characters.itemCount == 0

    when {
        isInitialLoad -> ProgressIndicatorFillMaxSize(bottomPadding)
        isError -> ErrorUi(
            modifier = Modifier.padding(bottom = bottomPadding),
            button = ErrorButton { characters.retry() },
        )

        isListEmpty -> EmptyUi(modifier = Modifier.padding(bottom = bottomPadding))
        else -> CharactersLoaded(
            characters = characters,
            listState = listState,
            onAction = onAction,
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CharactersLoaded(
    characters: LazyPagingItems<CharacterState>,
    listState: LazyGridState,
    scrollBehavior: TopAppBarScrollBehavior,
    onAction: (CharactersListActions) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensions.components.cardInGrid.small),
        contentPadding = PaddingValues(dimensions.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.spacing.medium),
    ) {
        when (characters.loadState.prepend) {
            is Error -> item(key = "prepend error", span = { GridItemSpan(maxLineSpan) }) {
                PaginationErrorGridItem(onRetry = { characters.retry() })
            }

            is Loading -> item(key = "prepend loading") { ProgressIndicatorGridItem() }
            else -> Unit
        }

        items(
            count = characters.itemCount,
            key = characters.itemKey { it.id },
        ) { index -> characters[index]?.let { character -> CharacterCard(character = character, onAction = onAction) } }

        when (characters.loadState.append) {
            is Error -> item(key = "append error", span = { GridItemSpan(maxLineSpan) }) {
                PaginationErrorGridItem(onRetry = { characters.retry() })
            }

            is Loading -> item(key = "append loading") { ProgressIndicatorGridItem() }
            else -> Unit
        }
    }
}

@Composable
private fun LazyGridItemScope.CharacterCard(
    character: CharacterState,
    onAction: (CharactersListActions) -> Unit,
) {
    Card(
        modifier = Modifier
            .testTag(GRID_CHARACTER_TEST_TAG)
            .aspectRatio(1f)
            .animateItem(),
        shape = shapes.roundedCorner.large,
        onClick = { onAction(ShowCharacterDetail(character.id)) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardImage(state = character.image)
            CardText(text = character.name)
        }
    }
}

@Composable
private fun ColumnScope.CardImage(
    state: CharacterImageState,
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
                textResId = R.string.characters_list_pagination_error,
                textAlign = TextAlign.Center,
            )
            Button(onClick = onRetry) {
                Text.Vanilla(R.string.characters_list_pagination_error_retry)
            }
        }
    }
}
