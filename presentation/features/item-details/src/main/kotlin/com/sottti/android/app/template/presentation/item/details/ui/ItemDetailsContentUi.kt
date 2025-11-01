package com.sottti.android.app.template.presentation.item.details.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.error.ErrorUi
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.images.network.NetworkImage
import com.sottti.android.app.template.presentation.item.details.model.ImageState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsRow
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsSectionState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Error
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Loaded
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState.Loading
import com.sottti.android.app.template.presentation.item.details.model.ItemIdentityState
import com.sottti.android.app.template.presentation.item.details.model.ItemState
import com.sottti.android.app.template.presentation.utils.Spacer
import androidx.compose.material3.ListItem as MaterialListItem

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
            is Loading -> ProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )

            is Error -> ErrorUi(modifier = Modifier.padding(padding))

            is Loaded -> LoadedState(
                nestedScrollConnection = scrollBehavior.nestedScrollConnection,
                onAction = onAction,
                padding = padding,
                state = state.item,
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
    state: ItemState,
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
        item(key = "image") { Image(state.imageState) }
        item(key = "identity") { DetailsSection(state.identity) }
    }
}

@Composable
private fun Image(
    state: ImageState,
) {
    NetworkImage(
        url = state.imageUrl,
        contentDescription = state.imageDescription,
        roundedCorners = true,
        modifier = Modifier
            .padding(horizontal = dimensions.spacing.medium)
            .fillMaxWidth()
            .aspectRatio(1.75f),
    )
}

@Composable
private fun DetailsSection(
    state: ItemDetailsSectionState,
) {
    Column(modifier = Modifier.padding(horizontal = dimensions.spacing.medium)) {
        Header(state.header)
        Spacer(size = dimensions.spacing.smallMedium)
        when (state) {
            is ItemIdentityState -> IdentityDetails(state = state)
        }
    }
}

@Composable
private fun Header(@StringRes text: Int) {
    Text.Label.Large(
        text = stringResource(text),
        textColor = colors.onBackground
    )
}

@Composable
private fun IdentityDetails(state: ItemIdentityState) {
    val items = listOfNotNull(state.name, state.tagline, state.year)

    DetailsCard {
        items.forEachIndexed { index, item ->
            ListItem(state = item, showBottomDivider = index < items.lastIndex)
        }
    }
}

@Composable
private fun DetailsCard(content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        content()
    }
}

@Composable
private fun ListItem(
    state: ItemDetailsRow,
    showBottomDivider: Boolean = true,
) {
    val windowWidthPx = LocalWindowInfo.current.containerSize.width
    val density = LocalDensity.current
    val screenWidth = with(density) { windowWidthPx.toDp() }
    MaterialListItem(
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        headlineContent = { Headline(screenWidth = screenWidth, state = state) },
        trailingContent = { Trailing(screenWidth = screenWidth, state = state) },
    )
    if (showBottomDivider) HorizontalDivider()
}

@Composable
private fun Headline(
    screenWidth: Dp,
    state: ItemDetailsRow,
) {
    Text.Body.Large(
        text = stringResource(state.headline),
        textColor = colors.onSurfaceVariant,
        modifier = Modifier.widthIn(max = screenWidth * 0.4f)
    )
}

@Composable
private fun Trailing(
    screenWidth: Dp,
    state: ItemDetailsRow,
) {
    state.trailing?.let { trailing ->
        Text.Body.Large(
            text = trailing,
            textColor = colors.onSurface,
            modifier = Modifier.widthIn(max = screenWidth * 0.5f),
            textAlign = TextAlign.End,
        )
    }
}
