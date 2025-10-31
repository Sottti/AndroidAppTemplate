package com.sottti.android.app.template.presentation.images.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.min
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicator
import com.sottti.android.app.template.presentation.design.system.progress.indicators.ProgressIndicatorSize
import com.sottti.android.app.template.presentation.design.system.shapes.compositionLocal.shapes
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.images.R
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun NetworkImage(
    url: ImageUrl,
    contentDescription: ImageContentDescription,
    modifier: Modifier = Modifier,
    roundedCorners: Boolean = false,
    foreverLoading: Boolean = false,
) {
    val isPreview = LocalInspectionMode.current
    val model = if (isPreview) R.drawable.img_android_statue else imageRequest(url)
    val painter = rememberAsyncImagePainter(model)
    val painterState by painter.state.collectAsStateWithLifecycle()
    val cornerRadius = when {
        roundedCorners -> shapes.roundedCorner.medium
        else -> RoundedCornerShape(ZeroCornerSize)
    }

    BoxWithConstraints(modifier = modifier.clip(cornerRadius)) {
        val imageModifier = Modifier.matchParentSize()

        val indicatorSize = when {
            min(maxWidth, maxHeight) < dimensions.components.cardInGrid.medium ->
                ProgressIndicatorSize.Small

            min(maxWidth, maxHeight) < dimensions.components.cardInGrid.large ->
                ProgressIndicatorSize.Medium

            else -> ProgressIndicatorSize.Large
        }

        when {
            foreverLoading || painterState is AsyncImagePainter.State.Loading ->
                ProgressIndicator(modifier = imageModifier, size = indicatorSize)

            painterState is AsyncImagePainter.State.Success ->
                Image(
                    painter = painter,
                    contentDescription = contentDescription.value,
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                )

            else -> Box(modifier = imageModifier)
        }
    }
}

@Composable
private fun imageRequest(url: ImageUrl): ImageRequest {
    val context = LocalContext.current
    return remember(url.value, context) {
        ImageRequest
            .Builder(context)
            .data(url.value)
            .crossfade(true)
            .build()
    }
}

@Composable
@AndroidAppTemplatePreview
internal fun NetworkImagePreview(
    @PreviewParameter(NetworkImageStateProvider::class)
    state: NetworkImageState,
) {
    AndroidAppTemplateTheme {
        NetworkImage(
            contentDescription = state.contentDescription,
            foreverLoading = state.foreverLoading,
            modifier = state.modifier,
            roundedCorners = state.roundedCorners,
            url = state.imageUrl,
        )
    }
}
