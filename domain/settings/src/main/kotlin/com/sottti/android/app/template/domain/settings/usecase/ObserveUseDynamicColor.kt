package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveUseDynamicColor @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<DynamicColor> =
        settingsRepository.observeDynamicColor()
}
