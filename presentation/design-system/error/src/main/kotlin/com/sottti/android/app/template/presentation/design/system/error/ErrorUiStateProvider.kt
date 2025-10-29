package com.sottti.android.app.template.presentation.design.system.error

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.fixtures.FixturesR

internal class ErrorUiStateProvider : PreviewParameterProvider<ErrorState?> {
    override val values: Sequence<ErrorState?> =
        sequence {
            yield(defaultError)
            yield(alternativeError)
        }
}

private val defaultError = null
private val alternativeError = ErrorState(
    illustration = Illustrations.AndroidRobotMatrix.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = FixturesR.fixture_button_text,
)
