package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetSystemColorContrast @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): SystemColorContrast =
        settingsRepository.getSystemColorContrast()
}
