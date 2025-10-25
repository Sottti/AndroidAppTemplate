package com.sottti.android.app.template.presentation.images.local

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.images.local.data.Images

internal class ImageStateProvider : PreviewParameterProvider<ImagePreviewState> {
    override val values = sequence {
        roundedCornerValues().forEach { roundedCorners ->
            stateValues().forEach { state ->
                modifierValues().forEach { modifier ->
                    yield(
                        ImagePreviewState(
                            state = state,
                            roundedCorners = roundedCorners,
                            modifier = modifier,
                        )
                    )
                }
            }
        }
    }
}

private fun modifierValues() = listOf(Modifier)
private fun roundedCornerValues() = listOf(false, true)
private fun stateValues() = listOf(Images.AndroidStatue.state)
