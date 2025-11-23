package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasTestTag
import com.sottti.android.app.template.presentation.design.system.progress.indicators.PROGRESS_INDICATOR_TEST_TAG

internal fun fullscreenIndicatorMatcher(): SemanticsMatcher =
    hasTestTag(PROGRESS_INDICATOR_TEST_TAG) and
        hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not()

internal fun gridIndicatorMatcher(): SemanticsMatcher =
    hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
