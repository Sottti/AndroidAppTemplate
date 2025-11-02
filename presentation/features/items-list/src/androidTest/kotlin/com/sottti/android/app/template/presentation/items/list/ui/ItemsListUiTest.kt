package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.ui.test.SemanticsMatcher
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
import com.sottti.android.app.template.presentation.utils.test.setOrientation
import org.junit.Test

internal class ItemsListUiTest(
    orientation: OrientationTest,
) : BaseUiTest(orientation) {

    private fun fullscreenIndicatorMatcher(): SemanticsMatcher =
        hasTestTag(PROGRESS_INDICATOR_TEST_TAG) and
                hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG).not()

    private fun gridIndicatorMatcher(): SemanticsMatcher =
        hasTestTag(PROGRESS_INDICATOR_GRID_TEST_TAG)

    @Test
    fun givenStateIsInitialLoad_whenUiIsRendered_thenFullScreenProgressBarsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadingState) }

        rule.onNode(fullscreenIndicatorMatcher()).assertIsDisplayed()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()

        rule.onNodeWithTag(PULL_TO_REFRESH_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun givenStateIsAppendLoading_whenUiIsRendered_thenAppendIndicatorAndItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateAppendLoading) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertIsDisplayed()

        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsPrependLoading_whenUiIsRendered_thenPrependIndicatorAndItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStatePrependLoading) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertIsDisplayed()

        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsAppendPrependLoading_whenUiIsRendered_thenAppendAndPrependIndicatorsAndItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateAppendPrependBothLoading) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodes(gridIndicatorMatcher()).assertCountEquals(2)
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedNoPagination_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateNoPagination) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateAppendEndReached_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateAppendEndReached) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStatePrependEndReached_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStatePrependEndReached) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateAppendPrependBothEndsReached_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateAppendPrependBothEndsReached) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
    }

    @Test
    fun givenStateIsLoadedStateLoadsOfItems_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateLoadsOfItems) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(10)
    }

    @Test
    fun givenStateIsLoadedStateLoadsOfItemsRefreshing_whenUiIsRendered_thenItemsAreVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = loadedStateLoadsOfItemsRefreshing) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertIsNotDisplayed()

        rule.onNodeWithTag(PULL_TO_REFRESH_TEST_TAG).assertIsDisplayed()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(10)
    }

    @Test
    fun givenStateIsEmptyState_whenUiIsRendered_thenEmptyIsVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = emptyState) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(0)
        rule.onNodeWithTag(EMPTY_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun givenStateIsErrorState_whenUiIsRendered_thenErrorIsVisible() {
        rule.setOrientation(orientation)

        rule.setContent { ItemListUiPreview(state = errorState) }

        rule.onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
        rule.onNode(gridIndicatorMatcher()).assertDoesNotExist()
        rule.onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(0)
        rule.onNodeWithTag(ERROR_TEST_TAG).assertIsDisplayed()
    }
}
