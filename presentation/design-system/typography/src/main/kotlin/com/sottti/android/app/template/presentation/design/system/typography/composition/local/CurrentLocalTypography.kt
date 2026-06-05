package com.sottti.android.app.template.presentation.design.system.typography.composition.local

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

public val typography: Typography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
