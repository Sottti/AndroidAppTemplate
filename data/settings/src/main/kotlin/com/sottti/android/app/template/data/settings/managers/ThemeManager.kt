package com.sottti.android.app.template.data.settings.managers

import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.flow.Flow

internal interface ThemeManager {
    fun getSystemTheme(): SystemTheme
    fun observeSystemTheme(): Flow<SystemTheme>
}
