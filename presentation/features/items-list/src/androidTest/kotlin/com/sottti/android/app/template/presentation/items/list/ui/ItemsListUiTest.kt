package com.sottti.android.app.template.presentation.items.list.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.sottti.android.app.template.presentation.design.system.empty.EMPTY_TEST_TAG
import com.sottti.android.app.template.presentation.design.system.error.ERROR_TEST_TAG
import com.sottti.android.app.template.presentation.utils.test.BaseUiTest
import com.sottti.android.app.template.presentation.utils.test.FontScalesTest
import com.sottti.android.app.template.presentation.utils.test.OrientationTest
import org.junit.Test

internal class ItemsListUiTest(
    fontScale: FontScalesTest,
    orientation: OrientationTest,
) : BaseUiTest(fontScale, orientation) {

    @Test
    fun givenStateIsInitialLoad_thenFullScreenProgressBarsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadingState) }) {
            onNode(fullscreenIndicatorMatcher()).assertIsDisplayed()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()

            onNodeWithTag(PULL_TO_REFRESH_TEST_TAG).assertIsDisplayed()
        }

    @Test
    fun givenStateIsAppendLoading_thenAppendIndicatorAndItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateAppendLoading) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertIsDisplayed()

            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsPrependLoading_thenPrependIndicatorAndItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStatePrependLoading) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertIsDisplayed()

            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsAppendPrependLoading_thenAppendAndPrependIndicatorsAndItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateAppendPrependBothLoading) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onAllNodes(gridIndicatorMatcher()).assertCountEquals(2)
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsLoadedNoPagination_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateNoPagination) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsLoadedStateAppendEndReached_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateAppendEndReached) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsLoadedStatePrependEndReached_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStatePrependEndReached) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsLoadedStateAppendPrependBothEndsReached_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateAppendPrependBothEndsReached) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(2)
        }

    @Test
    fun givenStateIsLoadedStateLoadsOfItems_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateLoadsOfItems) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(10)
        }

    @Test
    fun givenStateIsLoadedStateLoadsOfItemsRefreshing_thenItemsAreVisible() =
        runUiTest(content = { ItemListUiPreview(loadedStateLoadsOfItemsRefreshing) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertIsNotDisplayed()

            onNodeWithTag(PULL_TO_REFRESH_TEST_TAG).assertIsDisplayed()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(10)
        }

    @Test
    fun givenStateIsEmptyState_thenEmptyIsVisible() =
        runUiTest(content = { ItemListUiPreview(emptyState) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(0)
            onNodeWithTag(EMPTY_TEST_TAG).assertIsDisplayed()
        }

    @Test
    fun givenStateIsErrorState_thenErrorIsVisible() =
        runUiTest(content = { ItemListUiPreview(errorState) }) {
            onNode(fullscreenIndicatorMatcher()).assertDoesNotExist()
            onNode(gridIndicatorMatcher()).assertDoesNotExist()
            onAllNodesWithTag(GRID_ITEM_TEST_TAG).assertCountEquals(0)
            onNodeWithTag(ERROR_TEST_TAG).assertIsDisplayed()
        }
}
