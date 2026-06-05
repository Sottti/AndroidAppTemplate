package com.sottti.android.app.template.presentation.characters.list.data

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.characters.fixtures.listOfTwoCharacters
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharactersFake
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions.ShowCharacterDetail
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManagerFake
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.CharacterDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CharactersListViewModelTest {

    private val characters = listOfTwoCharacters
    private val pagingData = PagingData.from(characters)
    private val pagingFlow = flowOf(pagingData)

    private lateinit var navigationManager: NavigationManagerFake
    private lateinit var observeCharacters: ObserveCharactersFake

    @Before
    fun setup() {
        navigationManager = NavigationManagerFake()
        observeCharacters = ObserveCharactersFake()
    }

    @Test
    fun `maps domain characters to ui models`() = runTest {
        observeCharacters.setCharacters(pagingFlow)

        val viewModel = CharactersListViewModel(
            observeCharacters = observeCharacters,
            navigationManager = navigationManager,
            testScope = this,
        )
        val snapshot = viewModel.state.value.characters.asSnapshot()
        advanceUntilIdle()
        assertThat(snapshot).containsExactlyElementsIn(characters.map { it.toUi() })
    }

    @Test
    fun `empty paging emits empty list`() = runTest {
        observeCharacters.setCharacters(flowOf(PagingData.from(emptyList())))

        val viewModel = CharactersListViewModel(
            observeCharacters = observeCharacters,
            navigationManager = navigationManager,
            testScope = this,
        )

        val snapshot = viewModel.state.value.characters.asSnapshot()
        advanceUntilIdle()
        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `show detail navigates to CharacterDetailFeature`() = runTest {
        observeCharacters.setCharacters(pagingFlow)

        val viewModel = CharactersListViewModel(
            observeCharacters = observeCharacters,
            navigationManager = navigationManager,
            testScope = this,
        )

        val characterId = listOfTwoCharacters.first().id.value

        viewModel.onAction(ShowCharacterDetail(characterId = characterId))

        navigationManager.commands().test {
            assertThat(awaitItem())
                .isEqualTo(NavigationCommand.NavigateTo(CharacterDetail(characterId = characterId)))
            cancelAndIgnoreRemainingEvents()
        }
    }
}
