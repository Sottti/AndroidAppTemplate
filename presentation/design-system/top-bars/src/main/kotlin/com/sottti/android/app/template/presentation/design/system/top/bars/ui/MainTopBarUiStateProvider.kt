package com.sottti.android.app.template.presentation.design.system.top.bars.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.fixtures.R

internal class MainTopBarUiStateProvider : PreviewParameterProvider<MainTopBarState> {
    @OptIn(ExperimentalMaterial3Api::class)
    override val values: Sequence<MainTopBarState> = sequence {
        showTitleValues().forEach { showTitle ->
            titleResIdValues().forEach { titleResId ->
                yield(
                    MainTopBarState(
                        showTitle = showTitle,
                        titleResId = titleResId,
                        scrollBehavior = null
                    )
                )
            }
        }
    }
}

private fun showTitleValues() = listOf(false, true)
private fun titleResIdValues() = listOf(
    null,
    R.string.fixture_title,
    R.string.fixture_title_long,
)
