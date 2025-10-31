package com.sottti.android.app.template.presentation.design.system.icons.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.design.system.icons.data.Icons

internal class IconStateProvider : PreviewParameterProvider<IconState> {
    override val values: Sequence<IconState> = sequence {
        stateValues().forEach { state ->
            crossfadeValues().forEach { crossfade ->
                onClickValues().forEach { onClick ->
                    yield(
                        IconState(
                            crossfade = crossfade,
                            onClick = onClick,
                            iconState = state,
                        )
                    )
                }
            }
        }
    }
}

private fun crossfadeValues() = sequenceOf(false, true)
private fun onClickValues() = sequenceOf({}, null)
private fun stateValues() = listOf(Icons.Arrow.Back.filled)
