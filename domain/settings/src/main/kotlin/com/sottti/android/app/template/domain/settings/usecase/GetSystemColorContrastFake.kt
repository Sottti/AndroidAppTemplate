package com.sottti.android.app.template.domain.settings.usecase

import com.sottti.android.app.template.domain.core.models.SystemColorContrast

internal class GetSystemColorContrastFake(
    private var contrast: SystemColorContrast = SystemColorContrast.StandardContrast,
) : GetSystemColorContrast {

    fun setContrast(newContrast: SystemColorContrast) {
        this.contrast = newContrast
    }

    override operator fun invoke(): SystemColorContrast = contrast
}
