package com.sottti.android.app.template.presentation.home.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.cores.models.DynamicColor
import com.sottti.android.app.template.domain.cores.models.SystemColorContrast
import com.sottti.android.app.template.domain.cores.models.SystemTheme

@Immutable
internal data class HomeState(
    val dynamicColor: DynamicColor,
    val systemColorContrast: SystemColorContrast,
    val systemTheme: SystemTheme,
)
