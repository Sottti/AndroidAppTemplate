package com.sottti.android.app.template.presentation.app.shell.model

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme

@Immutable
internal data class AppShellState(
    val dynamicColor: DynamicColor,
    val systemColorContrast: SystemColorContrast,
    val systemTheme: SystemTheme,
)
