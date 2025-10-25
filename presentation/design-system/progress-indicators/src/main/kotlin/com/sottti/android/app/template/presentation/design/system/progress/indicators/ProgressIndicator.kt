package com.sottti.android.app.template.presentation.design.system.progress.indicators

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun ProgressIndicator(
    modifier: Modifier = Modifier,
    size: ProgressIndicatorSize = ProgressIndicatorSize.Medium,
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            strokeWidth = strokeWidth(size),
            modifier = Modifier
                .align(Alignment.Center)
                .size(progressIndicatorSize(size)),
        )
    }
}

@Composable
@ReadOnlyComposable
private fun strokeWidth(
    size: ProgressIndicatorSize,
): Dp = when (size) {
    ProgressIndicatorSize.Small -> ProgressIndicatorDefaults.CircularStrokeWidth * 0.75f
    ProgressIndicatorSize.Medium -> ProgressIndicatorDefaults.CircularStrokeWidth
    ProgressIndicatorSize.Large -> ProgressIndicatorDefaults.CircularStrokeWidth
}

@Composable
@ReadOnlyComposable
private fun progressIndicatorSize(size: ProgressIndicatorSize): Dp =
    when (size) {
        ProgressIndicatorSize.Small -> dimensions.component.progressIndicator.small
        ProgressIndicatorSize.Medium -> dimensions.component.progressIndicator.medium
        ProgressIndicatorSize.Large -> dimensions.component.progressIndicator.large
    }

@Composable
@AndroidAppTemplatePreview
internal fun ProgressIndicatorPreview(
    @PreviewParameter(ProgressIndicatorStateProvider::class)
    modifier: Modifier,
) {
    AndroidAppTemplateTheme {
        ProgressIndicator(modifier)
    }
}
