package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme

public class GetSystemThemeFake(
    private var theme: SystemTheme = SystemTheme.LightSystemTheme,
) : GetSystemTheme {

    public fun setTheme(newTheme: SystemTheme) {
        this.theme = newTheme
    }

    override operator fun invoke(): SystemTheme = theme
}
