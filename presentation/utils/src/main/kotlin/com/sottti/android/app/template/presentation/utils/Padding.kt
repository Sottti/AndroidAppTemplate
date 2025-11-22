package com.sottti.android.app.template.presentation.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
@ReadOnlyComposable
public operator fun PaddingValues.plus(
    extraPadding: PaddingValues,
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = calculateTopPadding() + extraPadding.calculateTopPadding(),
        bottom = calculateBottomPadding() + extraPadding.calculateBottomPadding(),
        start = calculateStartPadding(layoutDirection) +
            extraPadding.calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection) +
            extraPadding.calculateEndPadding(layoutDirection),
    )
}
