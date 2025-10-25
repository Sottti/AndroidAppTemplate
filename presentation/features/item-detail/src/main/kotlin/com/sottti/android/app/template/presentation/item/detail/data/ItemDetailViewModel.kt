package com.sottti.android.app.template.presentation.item.detail.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailActions
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailActions.NavigateBack
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ItemDetailViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    val state: StateFlow<ItemDetailState> =
        MutableStateFlow(ItemDetailState.Idle).asStateFlow()

    internal val onAction: (ItemDetailActions) -> Unit = ::processAction
    private fun processAction(action: ItemDetailActions) =
        when (action) {
            NavigateBack -> navigationManager.navigateBack()
        }
}
