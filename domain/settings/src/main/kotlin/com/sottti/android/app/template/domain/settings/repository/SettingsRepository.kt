package com.sottti.android.app.template.domain.settings.repository

import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.flow.Flow

public interface SettingsRepository {
    public fun getSystemColorContrast(): SystemColorContrast
    public fun observeSystemColorContrast(): Flow<SystemColorContrast>

    public fun observeDynamicColor(): Flow<DynamicColor>

    public fun getSystemTheme(): SystemTheme
    public fun observeSystemTheme(): Flow<SystemTheme>
}
