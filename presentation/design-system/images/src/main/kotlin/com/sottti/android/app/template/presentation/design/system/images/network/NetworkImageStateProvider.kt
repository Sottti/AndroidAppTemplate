package com.sottti.android.app.template.presentation.design.system.images.network

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl

internal class NetworkImageStateProvider :
    PreviewParameterProvider<NetworkImageState> {
    override val values: Sequence<NetworkImageState> =
        sequence {
            modifierValues.forEach { modifier ->
                yield(imageState(loading = true, modifier = modifier, roundedCorners = false))
                roundedCornersValues.forEach { roundedCorners ->
                    yield(
                        imageState(
                            loading = false,
                            modifier = modifier,
                            roundedCorners = roundedCorners,
                        )
                    )
                }
            }
        }
}

private val roundedCornersValues = listOf(false, true)
private val modifierValues = listOf(
    Modifier.requiredSize(100.dp),
    Modifier.requiredSize(130.dp),
    Modifier.requiredSize(150.dp),
)
private val previewContentDescription = ImageContentDescription("content description")
private val previewImageUrl = ImageUrl("imageUrl")

private fun imageState(
    loading: Boolean,
    modifier: Modifier,
    roundedCorners: Boolean,
) = NetworkImageState(
    contentDescription = previewContentDescription,
    foreverLoading = loading,
    imageUrl = previewImageUrl,
    modifier = modifier,
    roundedCorners = roundedCorners,
)
