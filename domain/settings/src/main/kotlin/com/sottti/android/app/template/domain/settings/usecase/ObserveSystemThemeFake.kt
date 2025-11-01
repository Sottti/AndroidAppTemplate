package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

public class ObserveSystemThemeFake(
    initialValue: SystemTheme = SystemTheme.LightSystemTheme,
) : ObserveSystemTheme {

    private val flow = MutableStateFlow(initialValue)

    public suspend fun emit(newTheme: SystemTheme) {
        flow.emit(newTheme)
    }

    override fun invoke(): StateFlow<SystemTheme> = flow.asStateFlow()
}
