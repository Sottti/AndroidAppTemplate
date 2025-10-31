package com.sottti.android.app.template.data.settings.datasource.local.managers

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
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
