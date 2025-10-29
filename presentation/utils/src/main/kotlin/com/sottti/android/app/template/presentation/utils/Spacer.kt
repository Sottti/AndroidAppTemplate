package com.sottti.android.app.template.presentation.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.layout.Spacer as ComposeSpacer

@Composable
public fun Spacer(size: Dp) {
    ComposeSpacer(modifier = Modifier.size(size))
}

@Composable
public fun ColumnScope.Spacer(weight: Float) {
    ComposeSpacer(modifier = Modifier.weight(weight))
}

@Composable
public fun RowScope.Spacer(weight: Float) {
    ComposeSpacer(modifier = Modifier.weight(weight))
}
