package com.sottti.android.app.template.presentation.items.list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sottti.android.app.template.presentation.items.list.R
import com.sottti.android.app.template.presentation.items.list.model.ItemUiModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.usecase.ObserveItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class ItemsListViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val observeItems: ObserveItems,
) : ViewModel() {

    private val items: Flow<PagingData<ItemUiModel>> =
        observeItems()
            .map { pagingData -> pagingData.map { item -> item.toUi() } }
            .cachedIn(viewModelScope)

    val state: StateFlow<ItemsListState> =
        MutableStateFlow(
            ItemsListState(
                titleResId = R.string.items_list_title,
                items = items,
            )
        ).asStateFlow()

    internal val onAction: (ItemsListActions) -> Unit = ::processAction
    private fun processAction(
        action: ItemsListActions,
    ) = when (action) {
        ShowDetail -> navigationManager.navigateTo(ItemDetailFeature)
    }
}
