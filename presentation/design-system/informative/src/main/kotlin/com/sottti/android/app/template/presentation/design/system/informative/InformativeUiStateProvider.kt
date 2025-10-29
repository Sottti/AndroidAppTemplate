package com.sottti.android.app.template.presentation.design.system.informative

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.fixtures.FixturesR

internal class InformativeUiStateProvider : PreviewParameterProvider<InformativeState> {
    override val values: Sequence<InformativeState> =
        sequence {
            yield(informativeWithButton)
            yield(informativeWithoutButton)
        }
}

private val informativeWithButton = InformativeState(
    illustration = Illustrations.FamilyBeachSunset.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = FixturesR.fixture_button_text,
)

private val informativeWithoutButton = InformativeState(
    illustration = Illustrations.FamilyBeachSunset.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
    buttonText = null,
)
