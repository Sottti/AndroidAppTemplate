package com.sottti.android.app.template.data.settings.managers.fakes

import com.sottti.android.app.template.data.settings.managers.SystemColorContrastManager
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FakeSystemColorContrastManager(
    default: SystemColorContrast,
) : SystemColorContrastManager {

    private val contrastFlow = MutableStateFlow(default)

    fun setContrast(contrast: SystemColorContrast) {
        contrastFlow.value = contrast
    }

    override fun getSystemColorContrast(): SystemColorContrast =
        contrastFlow.value

    override fun observeSystemColorContrast(): Flow<SystemColorContrast> = contrastFlow
}
