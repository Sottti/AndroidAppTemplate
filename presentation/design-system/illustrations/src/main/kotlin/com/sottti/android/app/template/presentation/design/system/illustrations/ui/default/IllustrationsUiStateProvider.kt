package com.sottti.android.app.template.presentation.design.system.illustrations.ui.default

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations

internal class IllustrationUiStateProvider : PreviewParameterProvider<IllustrationPreviewState?> {
    override val values: Sequence<IllustrationPreviewState?> =
        sequence {
            yield(defaultIllustration)
            yield(circledIllustration)
        }
}

private val defaultIllustration =
    IllustrationPreviewState(
        state = Illustrations.FamilyBeachSunset.state,
        circled = false,
        modifier = Modifier,
    )

private val circledIllustration =
    IllustrationPreviewState(
        state = Illustrations.FamilyBeachSunset.state,
        circled = true,
        modifier = Modifier,
    )
