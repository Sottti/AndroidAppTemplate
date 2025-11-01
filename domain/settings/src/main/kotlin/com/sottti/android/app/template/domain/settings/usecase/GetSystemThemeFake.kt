package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme

internal class GetSystemThemeFake(
    private var theme: SystemTheme = SystemTheme.LightSystemTheme,
) : GetSystemTheme {

    fun setTheme(newTheme: SystemTheme) {
        this.theme = newTheme
    }

    override operator fun invoke(): SystemTheme = theme
}
