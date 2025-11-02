package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.sottti.android.app.template.presentation.design.system.empty.EMPTY_TEST_TAG
import com.sottti.android.app.template.presentation.design.system.error.ERROR_TEST_TAG
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
    fun givenStateIsAppendLoading_whenUiIsRendered_thenAppendIndicatorAndItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateAppendLoading) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
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
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsAppendPrependLoading_whenUiIsRendered_thenAppendAndPrependIndicatorsAndItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateAppendPrependBothLoading) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertCountEquals(2)

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedNoPagination_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateNoPagination) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateAppendEndReached_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateAppendEndReached) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStatePrependEndReached_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStatePrependEndReached) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateAppendPrependBothEndsReached_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateAppendPrependBothEndsReached) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        )
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateLoadsOfItems_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateLoadsOfItems) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        ).assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(10)
    }

    @Test
    fun givenStateIsLoadedStateLoadsOfItemsRefreshing_whenUiIsRendered_thenItemsAreVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = loadedStateLoadsOfItemsRefreshing) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        ).assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onNodeWithTag(PULL_TO_REFRESH_TEST_TAG)
            .assertIsDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(10)
    }

    @Test
    fun givenStateIsErrorState_whenUiIsRendered_thenEmptyIsVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = emptyState) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        ).assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(0)

        rule
            .onNodeWithTag(ERROR_TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun givenStateIsErrorState_whenUiIsRendered_thenErrorIsVisible() {
        when (orientation) {
            OrientationTest.Portrait -> rule.setPortrait()
            OrientationTest.Landscape -> rule.setLandscape()
        }

        rule.setContent { ItemListUiPreview(state = errorState) }

        rule.onNode(
            hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not() and
                    hasTestTag(PROGRESS_INDICATOR_TEST_TAG)
        ).assertIsNotDisplayed()

        rule
            .onNodeWithTag(PROGRESS_INDICATOR_GRID_TEST_TAG)
            .assertIsNotDisplayed()

        rule
            .onAllNodesWithTag(GRID_ITEM_TEST_TAG)
            .assertCountEquals(0)

        rule
            .onNodeWithTag(EMPTY_TEST_TAG)
            .assertIsDisplayed()
    }
}
