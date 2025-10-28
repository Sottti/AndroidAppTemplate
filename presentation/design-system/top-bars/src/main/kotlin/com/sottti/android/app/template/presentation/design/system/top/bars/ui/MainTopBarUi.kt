package com.sottti.android.app.template.presentation.design.system.top.bars.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
@OptIn(ExperimentalMaterial3Api::class)
public fun MainTopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    showTitle: Boolean = true,
    titleResId: Int? = null,
) {
    val colors = when (scrollBehavior) {
        null -> TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        else -> TopAppBarDefaults.topAppBarColors()
    }

    TopAppBar(
        colors = colors,
        scrollBehavior = scrollBehavior,
        title = { Title(titleResId, showTitle) },
    )
}

@Composable
private fun Title(
    @StringRes titleResId: Int?,
    showTitle: Boolean,
) {
    titleResId ?: return
    val title = stringResource(titleResId)
    AnimatedVisibility(
        visible = title.isNotEmpty() && showTitle,
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
