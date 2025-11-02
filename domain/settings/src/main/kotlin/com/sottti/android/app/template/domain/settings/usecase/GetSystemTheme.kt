package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemTheme

public fun interface GetSystemTheme {
    public operator fun invoke(): SystemTheme
}
