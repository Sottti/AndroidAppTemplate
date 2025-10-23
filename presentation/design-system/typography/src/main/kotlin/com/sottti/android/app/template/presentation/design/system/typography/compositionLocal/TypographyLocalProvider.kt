package com.sottti.android.app.template.presentation.design.system.typography.compositionLocal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sottti.android.app.template.presentation.design.system.typography.typography

@Composable
public fun TypographyLocalProvider(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalTypography provides typography()) {
        content()
    }
}
