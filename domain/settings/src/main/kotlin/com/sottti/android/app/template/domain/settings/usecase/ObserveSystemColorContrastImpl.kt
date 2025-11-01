package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveSystemColorContrastImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ObserveSystemColorContrast {
    override operator fun invoke(): Flow<SystemColorContrast> =
        settingsRepository.observeSystemColorContrast()
}
