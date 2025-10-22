package com.sottti.android.app.template.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.seconds

context(viewModel: ViewModel)
public fun <T> Flow<T>.stateInWhileSubscribed(
    initialValue: T,
): StateFlow<T> = stateIn(
    scope = viewModel.viewModelScope,
    started = WhileSubscribed(stopTimeoutMillis = 5.seconds.inWholeMilliseconds),
    initialValue = initialValue,
)
