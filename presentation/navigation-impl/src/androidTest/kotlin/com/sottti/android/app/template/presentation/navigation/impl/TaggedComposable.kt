package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
internal fun TaggedComposable(
    tag: String,
    content: @Composable () -> Unit = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .testTag(tag)) {
        content()
    }
}
