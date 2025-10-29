package com.sottti.android.app.template.presentation.design.system.informative

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.illustrations.ui.CircledIllustration
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview
import com.sottti.android.app.template.presentation.utils.Spacer

@Composable
public fun InformativeUi(
    illustration: IllustrationState,
    @StringRes primaryText: Int,
    @StringRes secondaryText: Int,
    modifier: Modifier = Modifier,
    button: InformativeButton? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensions.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(weight = 1f)
        CircledIllustration(
            state = illustration,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f),
        )
        Spacer(weight = 0.5f)
        Text.Headline.Medium(
            text = stringResource(primaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )
        Spacer(size = dimensions.spacing.small)
        Text.Body.Medium(
            text = stringResource(secondaryText),
            textColor = colors.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(weight = 1f)
        button?.let {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = button.onClick
            ) {
                Text.Vanilla(button.text)
            }
        }
    }
}

@Composable
@AndroidAppTemplatePreview
internal fun InformativeUiPreview(
    @PreviewParameter(InformativeUiStateProvider::class)
    state: InformativeState,
) {
    AndroidAppTemplateTheme {
        InformativeUi(
            modifier = Modifier.background(colors.background),
            illustration = state.illustration,
            primaryText = state.primaryText,
            secondaryText = state.secondaryText,
            button = toButton(state.buttonText),
        )
    }
}

@Composable
@ReadOnlyComposable
private fun toButton(
    @StringRes text: Int?,
): InformativeButton? =
    text?.let {
        InformativeButton(
            text = text,
            onClick = {},
        )
    }
