package com.sottti.android.app.template.data.settings.datasource.local.managers

import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class ThemeManagerFake(
    default: SystemTheme,
) : ThemeManager {

    private val themeStateFlow = MutableStateFlow(default)

    fun setTheme(theme: SystemTheme) {
        themeStateFlow.value = theme
    }

    override fun getSystemTheme(): SystemTheme {
        return themeStateFlow.value
    }

    override fun observeSystemTheme(): Flow<SystemTheme> {
        return themeStateFlow
    }
}
