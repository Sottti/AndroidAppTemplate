package com.sottti.android.app.template.presentation.design.system.typography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
public fun TypographyLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalTypography provides typography()) {
        content()
    }
}
