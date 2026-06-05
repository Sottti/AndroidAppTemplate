package com.sottti.android.app.template.presentation.app.shell.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.settings.usecase.GetSystemColorContrast
import com.sottti.android.app.template.domain.settings.usecase.GetSystemTheme
import com.sottti.android.app.template.domain.settings.usecase.ObserveDynamicColor
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemColorContrast
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemTheme
import com.sottti.android.app.template.presentation.app.shell.model.AppShellState
import com.sottti.android.app.template.presentation.utils.stateInWhileSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
internal class AppShellViewModel @Inject constructor(
    getSystemColorContrast: GetSystemColorContrast,
    getSystemTheme: GetSystemTheme,
    observeSystemColorContrast: ObserveSystemColorContrast,
    observeSystemTheme: ObserveSystemTheme,
    observeDynamicColor: ObserveDynamicColor,
) : ViewModel() {

    val initialState = initialState(
        systemColorContrast = getSystemColorContrast(),
        systemTheme = getSystemTheme(),
    )

    val state: StateFlow<AppShellState> = combine(
        flow = observeDynamicColor(),
        flow2 = observeSystemColorContrast(),
        flow3 = observeSystemTheme(),
    ) { dynamicColor, systemColorContrast, systemTheme ->
        reducer(dynamicColor, systemColorContrast, systemTheme)
    }.scan(initialState) { previous, reduce -> reduce(previous) }
        .drop(1)
        .stateInWhileSubscribed(initialValue = initialState)

    @Suppress("Wrapping", "Indentation")
    private val reducer: (
        dynamicColor: DynamicColor,
        systemColorContrast: SystemColorContrast,
        systemTheme: SystemTheme,
    ) -> (AppShellState) -> AppShellState =
        { dynamicColor, systemColorContrast, systemTheme ->
            { previous ->
                previous.reduce(
                    dynamicColor = dynamicColor,
                    systemColorContrast = systemColorContrast,
                    systemTheme = systemTheme,
                )
            }
        }
}
