package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveSystemTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): Flow<SystemTheme> =
        settingsRepository.observeSystemTheme()
}
