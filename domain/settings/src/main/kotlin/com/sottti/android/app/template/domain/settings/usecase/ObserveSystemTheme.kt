package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme
import kotlinx.coroutines.flow.Flow

public fun interface ObserveSystemTheme {
    public operator fun invoke(): Flow<SystemTheme>
}
