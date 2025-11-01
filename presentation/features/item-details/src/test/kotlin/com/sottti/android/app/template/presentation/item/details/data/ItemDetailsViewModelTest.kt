package com.sottti.android.app.template.presentation.item.details.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions.NavigateBack
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManagerFake
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.usecase.ObserveItemFake
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ItemDetailsViewModelTest {

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
                assertThat(awaitItem()).isInstanceOf(ItemDetailsState.Loading::class.java)

                val loadedState = awaitItem()
                assertThat(loadedState).isInstanceOf(ItemDetailsState.Loaded::class.java)

                val itemState = (loadedState as ItemDetailsState.Loaded).item
                assertThat(itemState.identity.name.trailing).isEqualTo(fixtureItem1.name.value)
                assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem1.name.value)
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
