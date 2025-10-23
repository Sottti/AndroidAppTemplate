package com.sottti.android.app.template.data.settings.managers

import com.sottti.android.app.template.domain.cores.models.SystemColorContrast
import kotlinx.coroutines.flow.Flow

internal interface SystemColorContrastManager {
    fun getSystemColorContrast(): SystemColorContrast
    fun observeSystemColorContrast(): Flow<SystemColorContrast>
}
