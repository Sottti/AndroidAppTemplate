package com.sottti.android.app.template.presentation.design.system.empty

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.fixtures.FixturesR

internal class EmptyUiStateProvider : PreviewParameterProvider<EmptyState?> {
    override val values: Sequence<EmptyState?> =
        sequence {
            yield(emptyDefault)
            yield(emptyAlternative)
        }
}

private val emptyDefault = null
private val emptyAlternative = EmptyState(
    illustration = Illustrations.FamilyBeachSunset.state,
    primaryText = FixturesR.fixture_primary_text,
    secondaryText = FixturesR.fixture_secondary_text,
)
