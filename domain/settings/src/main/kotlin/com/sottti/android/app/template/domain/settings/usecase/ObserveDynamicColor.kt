package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.DynamicColor
import kotlinx.coroutines.flow.Flow

public interface ObserveDynamicColor {
    public operator fun invoke(): Flow<DynamicColor>
}
