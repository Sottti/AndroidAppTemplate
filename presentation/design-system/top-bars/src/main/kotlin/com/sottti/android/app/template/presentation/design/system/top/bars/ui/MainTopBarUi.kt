package com.sottti.android.app.template.presentation.design.system.top.bars.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.icons.data.Icons
import com.sottti.android.app.template.presentation.design.system.icons.ui.Icon
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    @StringRes titleResId: Int? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    showTitle: Boolean = true,
) {
    val titleText = titleResId?.let { stringResource(id = it) }
    MainTopBar(
        onNavigationIconClick = onNavigationIconClick,
        scrollBehavior = scrollBehavior,
        showTitle = showTitle,
        title = titleText,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    onNavigationIconClick: (() -> Unit)?,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    showTitle: Boolean = true,
    title: String? = null,
) {
    val colors = when (scrollBehavior) {
        null -> TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        else -> TopAppBarDefaults.topAppBarColors()
    }

    TopAppBar(
        colors = colors,
        scrollBehavior = scrollBehavior,
        title = { Title(title, showTitle) },
        navigationIcon = { onNavigationIconClick?.let { NavigationIcon(it) } },
        modifier = Modifier.testTag(MAIN_TOP_BAR_TEST_TAG),
    )
}

@Composable
private fun NavigationIcon(
    onBackNavigation: () -> Unit,
) {
    Box(modifier = Modifier.testTag(MAIN_TOP_BAR_BACK_NAVIGATION_TEST_TAG)) {
        Icon(
            modifier = Modifier.testTag(MAIN_TOP_BAR_BACK_NAVIGATION_TEST_TAG),
            iconState = Icons.Arrow.Back.filled,
            onClick = { onBackNavigation() },
        )
    }
}

@Composable
private fun Title(
    title: String?,
    showTitle: Boolean,
) {
    if (title.isNullOrBlank()) return

    AnimatedVisibility(
        visible = showTitle,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        Text.Vanilla(text = title)
    }
}

@Composable
@AndroidAppTemplatePreview
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainTopBarPreview(
    @PreviewParameter(MainTopBarUiStateProvider::class)
    state: MainTopBarState,
) {
    AndroidAppTemplateTheme {
        MainTopBar(
            scrollBehavior = state.scrollBehavior,
            showTitle = state.showTitle,
            titleResId = state.titleResId,
        )
    }
}
