package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveDynamicColorImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ObserveDynamicColor {
    override operator fun invoke(): Flow<DynamicColor> =
        settingsRepository.observeDynamicColor()
}
