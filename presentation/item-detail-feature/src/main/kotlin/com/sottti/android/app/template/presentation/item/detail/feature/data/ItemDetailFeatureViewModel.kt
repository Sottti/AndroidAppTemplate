package com.sottti.android.app.template.presentation.item.detail.feature.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureActions
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureActions.*
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class ItemDetailFeatureViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val initialState = initialState()

    val state: StateFlow<ItemDetailFeatureState> = MutableStateFlow(initialState).asStateFlow()

    internal val onAction: (ItemDetailFeatureActions) -> Unit = ::processAction
    private fun processAction(action: ItemDetailFeatureActions) =
        when(action){
            NavigateBack -> navigationManager.navigateBack()
        }
}
