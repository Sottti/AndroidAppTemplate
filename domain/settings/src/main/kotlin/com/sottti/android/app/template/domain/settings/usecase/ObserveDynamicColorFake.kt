package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.DynamicColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

public class ObserveDynamicColorFake(
    initialValue: DynamicColor = DynamicColor(enabled = false),
) : ObserveDynamicColor {

    private val flow = MutableStateFlow(initialValue)

    public suspend fun emit(newDynamicColor: DynamicColor) {
        flow.emit(newDynamicColor)
    }

    override fun invoke(): StateFlow<DynamicColor> = flow.asStateFlow()
}
