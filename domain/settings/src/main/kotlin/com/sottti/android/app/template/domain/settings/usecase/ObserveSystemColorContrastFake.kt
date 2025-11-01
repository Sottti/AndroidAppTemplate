package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

public class ObserveSystemColorContrastFake(
    initialValue: SystemColorContrast = SystemColorContrast.StandardContrast,
) : ObserveSystemColorContrast {

    private val flow = MutableStateFlow(initialValue)

    public suspend fun emit(newContrast: SystemColorContrast) {
        flow.emit(newContrast)
    }

    override fun invoke(): StateFlow<SystemColorContrast> = flow.asStateFlow()
}
