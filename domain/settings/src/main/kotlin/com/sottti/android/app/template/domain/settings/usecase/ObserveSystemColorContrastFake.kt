package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ObserveSystemColorContrastFake(
    initialValue: SystemColorContrast = SystemColorContrast.StandardContrast,
) : ObserveSystemColorContrast {

    private val flow = MutableStateFlow(initialValue)

    suspend fun emit(newContrast: SystemColorContrast) {
        flow.emit(newContrast)
    }

    override fun invoke() = flow.asStateFlow()
}
