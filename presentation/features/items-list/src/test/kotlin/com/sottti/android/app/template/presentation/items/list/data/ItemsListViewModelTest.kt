package com.sottti.android.app.template.presentation.items.list.data

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.listOfTwoItems
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowItemDetail
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManagerFake
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetail
import com.sottti.android.app.template.usecase.ObserveItemsFake
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ItemsListViewModelTest {

    private val items = listOfTwoItems
    private val pagingData = PagingData.from(items)
    private val pagingFlow = flowOf(pagingData)

    private lateinit var navigationManager: NavigationManagerFake
    private lateinit var observeItems: ObserveItemsFake

    @Before
    fun setup() {
        navigationManager = NavigationManagerFake()
        observeItems = ObserveItemsFake()
    }

    @Test
    fun `maps domain items to ui models`() = runTest {
        observeItems.setItems(pagingFlow)

        val viewModel = ItemsListViewModel(
            observeItems = observeItems,
            navigationManager = navigationManager,
            testScope = this,
        )
        val snapshot = viewModel.state.value.items.asSnapshot()
        advanceUntilIdle()
        assertThat(snapshot).containsExactlyElementsIn(items.map { it.toUi() })
    }

    @Test
    fun `empty paging emits empty list`() = runTest {
        observeItems.setItems(flowOf(PagingData.from(emptyList())))

        val viewModel = ItemsListViewModel(
            observeItems = observeItems,
            navigationManager = navigationManager,
            testScope = this,
        )

        val snapshot = viewModel.state.value.items.asSnapshot()
        advanceUntilIdle()
        assertThat(snapshot).isEmpty()
    }

    @Test
    fun `show detail navigates to ItemDetailFeature`() = runTest {
        observeItems.setItems(pagingFlow)

        val viewModel = ItemsListViewModel(
            observeItems = observeItems,
            navigationManager = navigationManager,
            testScope = this,
        )

        val itemId = listOfTwoItems.first().id.value

        viewModel.onAction(ShowItemDetail(itemId = itemId))

        navigationManager.commands().test {
            assertThat(awaitItem())
                .isEqualTo(NavigationCommand.NavigateTo(ItemDetail(itemId = itemId)))
            cancelAndIgnoreRemainingEvents()
        }
    }
}
