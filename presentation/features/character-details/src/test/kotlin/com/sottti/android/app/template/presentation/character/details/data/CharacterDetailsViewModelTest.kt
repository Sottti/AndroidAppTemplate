package com.sottti.android.app.template.presentation.character.details.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacterFake
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsActions.NavigateBack
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManagerFake
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
internal class CharacterDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var navigationManager: NavigationManagerFake
    private lateinit var observeCharacter: ObserveCharacterFake
    private lateinit var viewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        navigationManager = NavigationManagerFake()
        observeCharacter = ObserveCharacterFake()
    }

    @Test
    fun `given viewmodel is created, when observing state, then initial state is Loading`() =
        runTest {
            observeCharacter.setCharacterFlow(flowOf(fixtureCharacter1))

            viewModel = CharacterDetailsViewModel(fixtureCharacter1.id, navigationManager, observeCharacter)

            assertThat(viewModel.state.value).isEqualTo(initialState)
        }

    @Test
    fun `given use case emits an character, when observing state, then it transitions to loaded`() =
        runTest {
            observeCharacter.setCharacterFlow(flowOf(fixtureCharacter1))
            viewModel = CharacterDetailsViewModel(fixtureCharacter1.id, navigationManager, observeCharacter)

            viewModel.state.test {
                advanceUntilIdle()
                val loadedState = viewModel.state.value
                assertThat(loadedState).isInstanceOf(CharacterDetailsState.Loaded::class.java)

                val characterState = (loadedState as CharacterDetailsState.Loaded).character
                assertThat(characterState.identity.name.trailing).isEqualTo(fixtureCharacter1.name.value)
                assertThat(loadedState.topBarState.title).isEqualTo(fixtureCharacter1.name.value)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given navigate back action, when on action is called, then it sends a navigate back command`() =
        runTest {
            observeCharacter.setCharacterFlow(flowOf(fixtureCharacter1))
            viewModel = CharacterDetailsViewModel(fixtureCharacter1.id, navigationManager, observeCharacter)

            viewModel.onAction(NavigateBack)

            navigationManager.commands().test {
                val command = awaitItem()
                assertThat(command).isEqualTo(NavigationCommand.NavigateBack)
                cancelAndIgnoreRemainingEvents()
            }
        }
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class MainDispatcherRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
