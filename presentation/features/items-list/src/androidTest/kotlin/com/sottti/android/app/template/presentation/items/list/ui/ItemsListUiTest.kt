package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem2
import com.sottti.android.app.template.presentation.design.system.progress.indicators.PROGRESS_INDICATOR_TEST_TAG
import com.sottti.android.app.template.presentation.utils.test.BaseUiTest
import com.sottti.android.app.template.presentation.utils.test.OrientationTest
import com.sottti.android.app.template.presentation.utils.test.setLandscape
import com.sottti.android.app.template.presentation.utils.test.setPortrait
import org.junit.Test

internal class ItemsListUiTest(
    orientation: OrientationTest,
) : BaseUiTest(orientation) {

    @Test
    fun givenStateIsInitialLoad_whenUiIsRendered_thenFullScreenProgressBarsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadingState) }

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_TEST_TAG)
            .assertIsDisplayed()

        rule
            .onNodeWithTag(PULL_TO_REFRESH_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun givenStateIsPrependLoading_whenUiIsRendered_thenPrependIndicatorAndItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStatePrependLoading) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsDisplayed()

        rule
            .onNodeWithText(fixtureItem1.name.value)
            .assertIsDisplayed()

        rule
            .onNodeWithText(fixtureItem2.name.value)
            .assertIsDisplayed()
    }
}
