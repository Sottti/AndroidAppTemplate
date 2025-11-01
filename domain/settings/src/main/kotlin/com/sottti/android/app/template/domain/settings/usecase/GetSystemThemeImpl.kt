package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import javax.inject.Inject

internal class GetSystemThemeImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : GetSystemTheme {
    override operator fun invoke(): SystemTheme =
        settingsRepository.getSystemTheme()
}
