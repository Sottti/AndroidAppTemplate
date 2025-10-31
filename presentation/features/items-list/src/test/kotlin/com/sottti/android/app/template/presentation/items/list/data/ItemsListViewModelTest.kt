package com.sottti.android.app.template.presentation.items.list.data

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.listOfTwoItems
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.navigation.manager.FakeNavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.usecase.FakeObserveItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ItemsListViewModelTest {


    private val items = listOfTwoItems
    private val pagingData = PagingData.from(items)
    private val pagingFlow = flowOf(pagingData)

    private lateinit var navigationManager: FakeNavigationManager
    private lateinit var observeItems: FakeObserveItems

    @Before
    fun setup() {
        navigationManager = FakeNavigationManager()
        observeItems = FakeObserveItems()
    }

    @Test
    fun `maps domain items to ui models`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))

        try {
            observeItems.setItems(pagingFlow)

            val viewModel = ItemsListViewModel(
                observeItems = observeItems,
                navigationManager = navigationManager,
                testScope = this,
            )
            val snapshot = viewModel.state.value.items.asSnapshot()
            assertThat(snapshot).containsExactlyElementsIn(items.map { it.toUi() })
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun `empty paging emits empty list`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))
        try {
            observeItems.setItems(flowOf(PagingData.from(emptyList())))

            val viewModel = ItemsListViewModel(
                observeItems = observeItems,
                navigationManager = navigationManager,
                testScope = this,
            )

            val snapshot = viewModel.state.value.items.asSnapshot()
            assertThat(snapshot).isEmpty()
        } finally {
            Dispatchers.resetMain()
        }
    }

    @Test
    fun `show detail navigates to ItemDetailFeature`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))
        try {
            observeItems.setItems(pagingFlow)

            val viewModel = ItemsListViewModel(
                observeItems = observeItems,
                navigationManager = navigationManager,
                testScope = this,
            )

            viewModel.onAction(ShowDetail)

            navigationManager.commands().test {
                val command = awaitItem()
                assertThat(command).isInstanceOf(NavigationCommand.NavigateTo::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        } finally {
            Dispatchers.resetMain()
        }
    }
}
