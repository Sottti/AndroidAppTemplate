package com.sottti.android.app.template.presentation.images.network

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.fixtures.fixtureContentDescription
import com.sottti.android.app.template.presentation.fixtures.fixtureImageUrl

internal class NetworkImageStateProvider :
    PreviewParameterProvider<NetworkImageState> {
    override val values: Sequence<NetworkImageState> =
        sequence {
            yield(imageState(loading = true, roundedCorners = false))
            roundedCornersValues.forEach { roundedCorners ->
                yield(imageState(loading = false, roundedCorners = roundedCorners))
            }
        }
}

private val roundedCornersValues = listOf(false, true)

private fun imageState(
    loading: Boolean,
    roundedCorners: Boolean,
) = NetworkImageState(
    contentDescription = fixtureContentDescription,
    foreverLoading = loading,
    imageUrl = fixtureImageUrl,
    roundedCorners = roundedCorners,
)
