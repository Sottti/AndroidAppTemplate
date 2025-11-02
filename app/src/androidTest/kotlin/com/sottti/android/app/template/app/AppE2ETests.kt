package com.sottti.android.app.template.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MAIN_TOP_BAR_BACK_NAVIGATION_TEST_TAG
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MAIN_TOP_BAR_TEST_TAG
import com.sottti.android.app.template.presentation.item.details.ui.ITEM_DETAILS_DETAILS_TEST_TAG
import com.sottti.android.app.template.presentation.item.details.ui.ITEM_DETAILS_IMAGE_TEST_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class AppE2ETest {

    @get:Rule(order = 0)
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<SplashScreenActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun fullUserJourney_scrollList_clickItem_navigateBack() {
        with(composeTestRule) {
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
