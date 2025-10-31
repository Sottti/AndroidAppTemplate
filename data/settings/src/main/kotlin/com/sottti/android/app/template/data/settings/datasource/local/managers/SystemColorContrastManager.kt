package com.sottti.android.app.template.data.settings.datasource.local.managers

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.flow.Flow

internal interface SystemColorContrastManager {
    fun getSystemColorContrast(): SystemColorContrast
    fun observeSystemColorContrast(): Flow<SystemColorContrast>
}
