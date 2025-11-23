package com.sottti.android.app.template.presentation.images.local

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.shapes
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.images.local.model.ImageState
import com.sottti.android.app.template.presentation.previews.PreviewAndroidAppTemplate
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Image(
    state: ImageState,
    modifier: Modifier = Modifier,
    roundedCorners: Boolean = true,
) {
    MaterialImage(
        painter = painterResource(id = state.resId),
        contentDescription = stringResource(state.descriptionResId),
        modifier = when {
            roundedCorners -> modifier.clip(shapes.roundedCorner.medium)
            else -> modifier
        },
    )
}

@Composable
@PreviewAndroidAppTemplate
internal fun ImagePreview(
    @PreviewParameter(ImageStateProvider::class)
    state: ImagePreviewState,
) {
    AndroidAppTemplateTheme {
        Image(
            modifier = state.modifier,
            roundedCorners = state.roundedCorners,
            state = state.state,
        )
    }
}
