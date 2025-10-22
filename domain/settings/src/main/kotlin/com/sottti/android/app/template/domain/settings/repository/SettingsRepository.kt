package com.sottti.android.app.template.domain.settings.repository

import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public fun observeDynamicColor(): Flow<DynamicColor>

    public fun getSystemTheme(): SystemTheme
    public fun observeSystemTheme(): Flow<SystemTheme>
}
