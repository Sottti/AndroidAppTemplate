package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.navigation.impl.fakes.CHARACTERS_LIST_FEATURE_TEST_TAG
import com.sottti.android.app.template.presentation.navigation.impl.fakes.CHARACTER_DETAIL_FEATURE_TEST_TAG
import com.sottti.android.app.template.presentation.navigation.impl.fakes.navigationEntriesFake
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManagerFake
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.CharacterDetail
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.CharactersList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class NavigatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navigationManager: NavigationManagerFake

    private val entryProvider: EntryProvider<NavKey> = navigationEntriesFake()


    @Before
    fun setUp() {
        navigationManager = NavigationManagerFake()
    }

    @Test
    fun when_navigator_is_created_then_the_initial_screen_is_displayed() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun given_initial_screen_when_navigate_to_command_is_emitted_then_the_new_screen_is_displayed() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        navigationManager.navigateTo(CharacterDetail(characterId = 1))

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onAllNodesWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertCountEquals(1)
    }

    @Test
    fun given_second_screen_when_navigate_back_command_is_emitted_then_the_initial_screen_is_displayed_again() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        navigationManager.navigateTo(CharacterDetail(characterId = 1))

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()

        navigationManager.navigateBack()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun given_initial_screen_when_navigate_back_command_is_emitted_then_the_initial_screen_remains_displayed() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()

        navigationManager.navigateBack()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun given_second_screen_when_navigate_to_root_command_is_emitted_then_only_the_root_screen_is_displayed() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        navigationManager.navigateTo(CharacterDetail(characterId = 1))

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        navigationManager.navigateToRoot(CharactersList)

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_navigate_to_same_screen_command_is_emitted_then_screen_remains_displayed_without_duplicates() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        navigationManager.navigateTo(CharactersList)

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onAllNodesWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertCountEquals(1)

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun given_root_when_navigate_to_root_then_root_remains_and_no_duplicates() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule.runOnIdle { navigationManager.navigateToRoot(CharactersList) }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onAllNodesWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertCountEquals(1)
    }

    @Test
    fun when_command_emitted_before_composition_then_it_is_applied_after_composition() {
        navigationManager.navigateTo(CharacterDetail(characterId = 1))

        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_manager_instance_changes_observer_reattaches() {
        var currentNavigationManager by mutableStateOf(navigationManager)
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(currentNavigationManager, entryProvider)
            }
        }

        val newManager = NavigationManagerFake()
        composeTestRule.runOnIdle { currentNavigationManager = newManager }

        composeTestRule.runOnIdle { newManager.navigateTo(CharacterDetail(characterId = 1)) }
        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()
    }

    @Test
    fun when_recomposed_state_changes_then_current_screen_persists() {
        var flip by mutableStateOf(false)
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Column {
                    if (flip) TaggedComposable("dummy") else TaggedComposable("dummy2")
                    Navigator(navigationManager, entryProvider)
                }
            }
        }

        composeTestRule.runOnIdle { navigationManager.navigateTo(CharacterDetail(characterId = 1)) }
        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule.runOnIdle { flip = !flip }

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_burst_to_and_back_in_same_frame_then_root_is_shown() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule.runOnIdle {
            navigationManager.navigateTo(CharacterDetail(characterId = 1))
            navigationManager.navigateBack()
        }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_two_navigators_listen_then_only_one_handles_single_event() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Column {
                    TaggedComposable("nav1") { Navigator(navigationManager, entryProvider) }
                    TaggedComposable("nav2") { Navigator(navigationManager, entryProvider) }
                }
            }
        }

        composeTestRule.runOnIdle { navigationManager.navigateTo(CharacterDetail(characterId = 1)) }

        composeTestRule
            .onAllNodesWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertCountEquals(1)
    }

    @Test
    fun when_disposed_and_recreated_then_starts_at_root() {
        var show by mutableStateOf(true)

        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                when {
                    show -> Navigator(navigationManager, entryProvider)
                    else -> TaggedComposable("disposed")
                }
            }
        }

        composeTestRule.runOnIdle { navigationManager.navigateTo(CharacterDetail(characterId = 1)) }
        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule.runOnIdle { show = false }
        composeTestRule
            .onNodeWithTag("disposed")
            .assertExists()

        composeTestRule.runOnIdle { show = true }

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_duplicate_navigate_to_on_character_then_no_duplicates() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule.runOnIdle { navigationManager.navigateTo(CharacterDetail(characterId = 1)) }
        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule.runOnIdle { navigationManager.navigateTo(CharacterDetail(characterId = 1)) }

        composeTestRule
            .onAllNodesWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertCountEquals(1)

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun when_alternating_burst_then_last_command_wins() {
        composeTestRule.setContent {
            AndroidAppTemplateTheme {
                Navigator(navigationManager, entryProvider)
            }
        }

        composeTestRule.runOnIdle {
            repeat(5) {
                navigationManager.navigateTo(CharacterDetail(characterId = 1))
                navigationManager.navigateToRoot(CharactersList)
            }
            navigationManager.navigateTo(CharacterDetail(characterId = 1))
        }

        composeTestRule
            .onNodeWithTag(CHARACTER_DETAIL_FEATURE_TEST_TAG)
            .assertExists()

        composeTestRule
            .onNodeWithTag(CHARACTERS_LIST_FEATURE_TEST_TAG)
            .assertDoesNotExist()
    }
}
