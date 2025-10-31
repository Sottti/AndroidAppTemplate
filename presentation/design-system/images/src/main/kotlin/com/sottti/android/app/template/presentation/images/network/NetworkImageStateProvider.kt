package com.sottti.android.app.template.presentation.images.network

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sottti.android.app.template.domain.core.fixtures.fixtureContentDescription
import com.sottti.android.app.template.domain.core.fixtures.fixtureImageUrl

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

private fun imageState(
    loading: Boolean,
    modifier: Modifier,
    roundedCorners: Boolean,
) = NetworkImageState(
    contentDescription = fixtureContentDescription,
    foreverLoading = loading,
    imageUrl = fixtureImageUrl,
    modifier = modifier,
    roundedCorners = roundedCorners,
)
