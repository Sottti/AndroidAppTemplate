package com.sottti.android.app.template.presentation.feature.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.presentation.feature.model.FeatureActions
import com.sottti.android.app.template.presentation.feature.model.FeatureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class FeatureViewModel @Inject constructor() : ViewModel() {

    private val initialState = initialState()

    private val actions = MutableSharedFlow<FeatureActions>(extraBufferCapacity = 64)
    val state: StateFlow<FeatureState> = MutableStateFlow(initialState).asStateFlow()

    internal val onAction: (FeatureActions) -> Unit = ::processAction
    private fun processAction(action: FeatureActions) = actions.tryEmit(action)
}
