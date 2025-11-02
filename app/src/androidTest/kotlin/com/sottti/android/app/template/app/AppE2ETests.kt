package com.sottti.android.app.template.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem8
import com.sottti.android.app.template.presentation.item.details.ui.ITEM_DETAILS_DETAILS_TEST_TAG
import com.sottti.android.app.template.presentation.items.list.ui.GRID_ITEM_TEST_TAG
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
            waitUntil(5000) {
                onAllNodes(hasText(fixtureItem1.name.value)).fetchSemanticsNodes().isNotEmpty()
            }

            onNodeWithText(fixtureItem1.name.value).assertIsDisplayed()

            onNodeWithTag(GRID_ITEM_TEST_TAG)
                .assertExists()
                .performScrollToNode(hasText(fixtureItem8.name.value))

            onNodeWithText(fixtureItem8.name.value).assertIsDisplayed()
            onNodeWithText(fixtureItem8.name.value).performClick()

            onNodeWithTag(ITEM_DETAILS_DETAILS_TEST_TAG).assertIsDisplayed()
            onNodeWithText(fixtureItem8.name.value).assertIsDisplayed()

            activity.onBackPressedDispatcher.onBackPressed()
            waitForIdle()

            onNodeWithTag(GRID_ITEM_TEST_TAG).assertIsDisplayed()
            onNodeWithText(fixtureItem1.name.value).assertIsDisplayed()
        }
    }
}
