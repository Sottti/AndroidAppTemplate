package com.sottti.android.app.template.presentation.items.list.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions
import com.sottti.android.app.template.presentation.items.list.model.ItemsListActions.ShowDetail
import com.sottti.android.app.template.presentation.items.list.model.ItemsListState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ItemsListViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    val state: StateFlow<ItemsListState> =
        MutableStateFlow(ItemsListState.Idle).asStateFlow()

    internal val onAction: (ItemsListActions) -> Unit = ::processAction
    private fun processAction(
        action: ItemsListActions,
    ) = when (action) {
        ShowDetail -> navigationManager.navigateTo(ItemDetailFeature)
    }
}
