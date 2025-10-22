package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetSystemTheme @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    public operator fun invoke(): SystemTheme =
        settingsRepository.getSystemTheme()
}
