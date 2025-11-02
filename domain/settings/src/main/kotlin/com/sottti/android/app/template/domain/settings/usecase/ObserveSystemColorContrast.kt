package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import kotlinx.coroutines.flow.Flow

public fun interface ObserveSystemColorContrast {
    public operator fun invoke(): Flow<SystemColorContrast>
}
