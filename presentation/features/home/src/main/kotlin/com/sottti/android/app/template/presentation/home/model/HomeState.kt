package com.sottti.android.app.template.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme

@Immutable
internal data class HomeState(
    val dynamicColor: DynamicColor,
    val systemColorContrast: SystemColorContrast,
    val systemTheme: SystemTheme,
)
