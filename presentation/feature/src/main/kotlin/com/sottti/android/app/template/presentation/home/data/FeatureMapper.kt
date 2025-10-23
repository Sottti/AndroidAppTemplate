package com.sottti.android.app.template.presentation.home.data

import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.presentation.design.system.colors.color.ColorContrast

internal fun SystemColorContrast.toColorContrast() =
    when (this) {
        SystemColorContrast.HighContrast -> ColorContrast.High
        SystemColorContrast.MediumContrast -> ColorContrast.Medium
        SystemColorContrast.StandardContrast -> ColorContrast.Standard
        SystemColorContrast.LowContrast -> ColorContrast.Low
    }
