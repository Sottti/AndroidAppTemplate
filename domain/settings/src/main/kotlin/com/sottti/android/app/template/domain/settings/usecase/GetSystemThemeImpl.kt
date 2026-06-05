package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import javax.inject.Inject

public class GetSystemThemeImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : GetSystemTheme {
    public override operator fun invoke(): SystemTheme =
        settingsRepository.getSystemTheme()
}
