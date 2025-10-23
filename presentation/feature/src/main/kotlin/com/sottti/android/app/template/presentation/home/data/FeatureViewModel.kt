package com.sottti.android.app.template.presentation.home.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.settings.usecase.GetSystemColorContrast
import com.sottti.android.app.template.domain.settings.usecase.GetSystemTheme
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemTheme
import com.sottti.android.app.template.domain.settings.usecase.ObserveUseDynamicColor
import com.sottti.android.app.template.presentation.home.model.FeatureActions
import com.sottti.android.app.template.presentation.home.model.FeatureActions.NoOp
import com.sottti.android.app.template.presentation.home.model.FeatureStateWrapper
import com.sottti.android.app.template.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
internal class FeatureViewModel @Inject constructor(
    getSystemColorContrast: GetSystemColorContrast,
    getSystemTheme: GetSystemTheme,
    observeSystemTheme: ObserveSystemTheme,
    observeUseDynamicColor: ObserveUseDynamicColor,
) : ViewModel() {

    val initialState = initialState(
        systemColorContrast = getSystemColorContrast(),
        systemTheme = getSystemTheme(),
    )

    private val actions = MutableSharedFlow<FeatureActions>(extraBufferCapacity = 64)
    val state: StateFlow<FeatureStateWrapper> = combine(
        flow = observeUseDynamicColor(),
        flow2 = observeSystemTheme(),
        flow3 = actions.onStart { emit(NoOp) },
    ) { dynamicColor, systemTheme, stateMutationAction ->
        reducer(dynamicColor, systemTheme, stateMutationAction)
    }.scan(initialState) { previous, reduce -> reduce(previous) }
        .drop(1)
        .stateInWhileSubscribed(initialValue = initialState)

    internal val onAction: (FeatureActions) -> Unit = ::processAction
    private fun processAction(action: FeatureActions) = actions.tryEmit(action)
    private val reducer: (
        dynamicColor: DynamicColor,
        systemTheme: SystemTheme,
        stateMutationAction: FeatureActions,
    ) -> (FeatureStateWrapper) -> FeatureStateWrapper =
        { dynamicColor, systemTheme, stateMutationAction ->
            { previous: FeatureStateWrapper ->
                previous.reduce(
                    dynamicColor = dynamicColor,
                    systemTheme = systemTheme,
                )
            }
        }
}
