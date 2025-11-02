package com.sottti.android.app.template.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MAIN_TOP_BAR_BACK_NAVIGATION_TEST_TAG
import com.sottti.android.app.template.presentation.item.details.ui.ITEM_DETAILS_DETAILS_TEST_TAG
import com.sottti.android.app.template.presentation.item.details.ui.ITEM_DETAILS_IMAGE_TEST_TAG
import com.sottti.android.app.template.presentation.utils.test.OrientationTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
internal class AppFlowsTest(
    orientation: OrientationTest,
) : IntegrationTest(orientation) {

    @Test
    fun fullUserJourney_scrollList_clickItem_navigateBack() {
        with(rule) {
            waitUntil() {
                onAllNodes(hasText(fixtureItem1.name.value))
                    .fetchSemanticsNodes()
                    .isNotEmpty()
            }

            onNodeWithText(fixtureItem1.name.value)
                .assertIsDisplayed()

            onNodeWithText(fixtureItem1.name.value).performClick()

            waitForIdle()

            onNodeWithTag(ITEM_DETAILS_IMAGE_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(ITEM_DETAILS_DETAILS_TEST_TAG).assertIsDisplayed()

            waitForIdle()

            onNodeWithTag(MAIN_TOP_BAR_BACK_NAVIGATION_TEST_TAG).performClick()

            waitForIdle()

            onNodeWithText(fixtureItem1.name.value)
                .assertIsDisplayed()
        }
    }
}
