package com.sottti.android.app.template.data.settings.managers

import com.sottti.android.app.template.domain.settings.model.SystemTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FakeThemeManager(
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
