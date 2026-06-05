package com.sottti.android.app.template.presentation.item.details.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.usecase.ObserveItemFake
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions.NavigateBack
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
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
internal class ItemDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var navigationManager: NavigationManagerFake
    private lateinit var observeItem: ObserveItemFake
    private lateinit var viewModel: ItemDetailsViewModel

    @Before
    fun setUp() {
        navigationManager = NavigationManagerFake()
        observeItem = ObserveItemFake()
    }

    @Test
    fun `given viewmodel is created, when observing state, then initial state is Loading`() =
        runTest {
            observeItem.setItemFlow(flowOf(fixtureItem1))

            viewModel = ItemDetailsViewModel(fixtureItem1.id, navigationManager, observeItem)

            assertThat(viewModel.state.value).isEqualTo(initialState)
        }

    @Test
    fun `given use case emits an item, when observing state, then it transitions to loaded`() =
        runTest {
            observeItem.setItemFlow(flowOf(fixtureItem1))
            viewModel = ItemDetailsViewModel(fixtureItem1.id, navigationManager, observeItem)

            viewModel.state.test {
                advanceUntilIdle()
                val loadedState = viewModel.state.value
                assertThat(loadedState).isInstanceOf(ItemDetailsState.Loaded::class.java)

                val itemState = (loadedState as ItemDetailsState.Loaded).item
                assertThat(itemState.identity.name.trailing).isEqualTo(fixtureItem1.name.value)
                assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem1.name.value)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given navigate back action, when on action is called, then it sends a navigate back command`() =
        runTest {
            observeItem.setItemFlow(flowOf(fixtureItem1))
            viewModel = ItemDetailsViewModel(fixtureItem1.id, navigationManager, observeItem)

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
