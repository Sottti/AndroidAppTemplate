package com.sottti.android.app.template.presentation.items.list.data

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.listOfTwoItems
import com.sottti.android.app.template.presentation.items.list.MainDispatcherRule
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.navigation.manager.FakeNavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateTo
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.usecase.FakeObserveItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ItemsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var navigationManager: FakeNavigationManager
    private lateinit var observeItems: FakeObserveItems
    private lateinit var viewModel: ItemsListViewModel

    @Before
    fun setUp() {
        navigationManager = FakeNavigationManager()
        observeItems = FakeObserveItems()
        viewModel = ItemsListViewModel(
            navigationManager = navigationManager,
            observeItems = observeItems,
        )
    }

    @Test
    fun `given items are available, when view model is created, then state contains the mapped paging data`() =
        runTest {
            observeItems.setItems(flowOf(PagingData.from(listOfTwoItems)))

            val itemsFlow = viewModel.state.first().items

            val snapshot = itemsFlow.asSnapshot(mainDispatcherRule)
            assertThat(snapshot).hasSize(2)
            assertThat(snapshot[0].name).isEqualTo(listOfTwoItems[0].name.value)
            assertThat(snapshot[1].id).isEqualTo(listOfTwoItems[1].id.value)
        }

    @Test
    fun `given show detail action is processed, when onAction is called, then it should navigate to item details feature`() =
        runTest {
            viewModel.onAction(ShowDetail)

            navigationManager.commands().test {
                val command = awaitItem()
                assertThat(command).isInstanceOf(NavigateTo::class.java)
                assertThat((command as NavigateTo).destination).isEqualTo(ItemDetailFeature)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given observeItems returns empty paging data, when view model is created, then state contains empty items`() =
        runTest {
            observeItems.setItems(flowOf(PagingData.empty()))

            val itemsFlow = viewModel.state.first().items

            val snapshot = itemsFlow.asSnapshot(mainDispatcherRule)
            assertThat(snapshot).isEmpty()
        }
}

private suspend fun <T : Any> Flow<PagingData<T>>.asSnapshot(
    mainDispatcherRule: MainDispatcherRule,
): List<T> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = object : androidx.recyclerview.widget.DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem == newItem
        },
        updateCallback = object : ListUpdateCallback {
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
            override fun onMoved(fromPosition: Int, toPosition: Int) {}
            override fun onChanged(position: Int, count: Int, payload: Any?) {}
        },
        workerDispatcher = mainDispatcherRule.testDispatcher,
    )
    differ.submitData(this.first())
    return differ.snapshot().items
}
