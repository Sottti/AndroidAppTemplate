package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast

public fun interface GetSystemColorContrast {
    public operator fun invoke(): SystemColorContrast
}
