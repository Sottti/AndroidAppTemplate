import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.listOfTwoItems
import com.sottti.android.app.template.presentation.items.list.data.ItemsListViewModel
import com.sottti.android.app.template.presentation.items.list.data.toUi
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.usecase.ObserveItems
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ItemsListViewModelTest {

    private val items = listOfTwoItems
    private val pagingData = PagingData.from(items)
    private val pagingFlow = flowOf(pagingData)

    @Test
    fun `maps domain items to ui models`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher(testScheduler))

        try {
            val observeItems = mockk<ObserveItems>()
            val navigationManager = mockk<NavigationManager>()
            coEvery { observeItems() } returns pagingFlow

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
            val observeItems = mockk<ObserveItems>()
            val navigationManager = mockk<NavigationManager>()
            coEvery { observeItems() } returns flowOf(PagingData.empty())

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
            val observeItems = mockk<ObserveItems>()
            val navigationManager = mockk<NavigationManager>(relaxed = true)
            coEvery { observeItems() } returns pagingFlow

            val viewModel = ItemsListViewModel(
                observeItems = observeItems,
                navigationManager = navigationManager,
                testScope = this,
            )

            viewModel.onAction(ShowDetail)

            coVerify { navigationManager.navigateTo(ItemDetailFeature) }
        } finally {
            Dispatchers.resetMain()
        }
    }
}
