package com.sottti.android.app.template.presentation.pully.list.feature.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.presentation.pully.list.feature.model.PullyListFeatureActions
import com.sottti.android.app.template.presentation.pully.list.feature.model.PullyListFeatureActions.ShowDetail
import com.sottti.android.app.template.presentation.pully.list.feature.model.PullyListFeatureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class PullyListFeatureViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val initialState = initialState()

    val state: StateFlow<PullyListFeatureState> = MutableStateFlow(initialState).asStateFlow()

    internal val onAction: (PullyListFeatureActions) -> Unit = ::processAction
    private fun processAction(
        action: PullyListFeatureActions,
    ) = when (action) {
        ShowDetail -> navigationManager.navigateTo(ItemDetailFeature)
    }
}
