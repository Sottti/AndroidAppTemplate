package com.sottti.android.app.template.presentation.design.system.error

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.informative.InformativeButton
import com.sottti.android.app.template.presentation.design.system.informative.InformativeUi
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun ErrorUi(
    modifier: Modifier = Modifier,
    button: ErrorButton? = null,
    illustration: IllustrationState = Illustrations.FamilyBeachSunset.state,
    @StringRes primaryText: Int = R.string.error_primary_text_default,
    @StringRes secondaryText: Int = R.string.error_secondary_text_default,
) {
    InformativeUi(
        illustration = illustration,
        primaryText = primaryText,
        secondaryText = secondaryText,
        modifier = modifier,
        button = button?.let {
            InformativeButton(
                text = button.text,
                onClick = button.onClick,
            )
        },
    )
}

@Composable
@AndroidAppTemplatePreview
internal fun ErrorUiPreview(
    @PreviewParameter(ErrorUiStateProvider::class)
    state: ErrorState?,
) {
    AndroidAppTemplateTheme {
        val modifier = Modifier.background(colors.background)
        when (state) {
            null -> ErrorUi(modifier = modifier)
            else -> ErrorUi(
                modifier = modifier,
                illustration = state.illustration,
                primaryText = state.primaryText,
                secondaryText = state.secondaryText,
                button = ErrorButton(
                    text = state.buttonText,
                    onClick = {},
                ),
            )
        }
    }
}
