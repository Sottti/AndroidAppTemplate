package com.sottti.android.app.template.presentation.design.system.empty

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.informative.InformativeUi
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun EmptyUi(
    modifier: Modifier = Modifier,
    illustration: IllustrationState = Illustrations.FamilyBeachSunset.state,
    @StringRes primaryText: Int = R.string.empty_primary_text_default,
    @StringRes secondaryText: Int = R.string.empty_secondary_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        modifier = modifier,
    )
}

@Composable
@AndroidAppTemplatePreview
internal fun EmptyUiPreview(
    @PreviewParameter(EmptyUiStateProvider::class)
    state: EmptyState?,
) {
    AndroidAppTemplateTheme {
        val modifier = Modifier.background(colors.background)
        when (state) {
            null -> EmptyUi(modifier = modifier)
            else -> EmptyUi(
                modifier = modifier,
                illustration = state.illustration,
                primaryText = state.primaryText,
                secondaryText = state.secondaryText,
            )
        }
    }
}
