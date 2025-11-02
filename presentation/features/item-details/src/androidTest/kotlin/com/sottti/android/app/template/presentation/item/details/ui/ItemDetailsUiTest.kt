package com.sottti.android.app.template.presentation.item.details.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.sottti.android.app.template.presentation.design.system.error.ERROR_TEST_TAG
import com.sottti.android.app.template.presentation.design.system.progress.indicators.PROGRESS_INDICATOR_TEST_TAG
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MAIN_TOP_BAR_TEST_TAG
import com.sottti.android.app.template.presentation.utils.test.BaseUiTest
import com.sottti.android.app.template.presentation.utils.test.FontScalesTest
import com.sottti.android.app.template.presentation.utils.test.OrientationTest
import org.junit.Test

internal class ItemDetailsUiTest(
    fontScale: FontScalesTest,
    orientation: OrientationTest,
) : BaseUiTest(fontScale, orientation) {

    @Test
    fun givenStateIsInitialLoad_thenFullScreenProgressBarsAreVisible() =
        runUiTest(content = { ItemDetailsUiPreview(loadingState) }) {
            onNode(hasTestTag(PROGRESS_INDICATOR_TEST_TAG)).assertIsDisplayed()
            onNodeWithTag(ERROR_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(ITEM_DETAILS_DETAILS_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(ITEM_DETAILS_IMAGE_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(MAIN_TOP_BAR_TEST_TAG).assertIsDisplayed()
        }

    @Test
    fun givenStateIsError_thenErrorIsVisible() =
        runUiTest(content = { ItemDetailsUiPreview(errorState) }) {
            onNode(hasTestTag(PROGRESS_INDICATOR_TEST_TAG)).assertDoesNotExist()
            onNodeWithTag(ERROR_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(ITEM_DETAILS_DETAILS_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(ITEM_DETAILS_IMAGE_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(MAIN_TOP_BAR_TEST_TAG).assertIsDisplayed()
        }

    @Test
    fun givenStateIsLoaded_thenImageAndDetailsAreVisible() =
        runUiTest(content = { ItemDetailsUiPreview(loadedState) }) {
            onNode(hasTestTag(PROGRESS_INDICATOR_TEST_TAG)).assertDoesNotExist()
            onNodeWithTag(ERROR_TEST_TAG).assertDoesNotExist()
            onNodeWithTag(ITEM_DETAILS_DETAILS_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(ITEM_DETAILS_IMAGE_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(MAIN_TOP_BAR_TEST_TAG).assertIsDisplayed()
        }
}
