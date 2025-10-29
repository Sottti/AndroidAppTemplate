package com.sottti.android.app.template.presentation.design.system.illustrations.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Illustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
) {
    MaterialImage(
        contentDescription = stringResource(state.descriptionResId),
        contentScale = ContentScale.Crop,
        modifier = modifier,
        painter = painterResource(id = state.resId),
    )
}

@Composable
@AndroidAppTemplatePreview
internal fun IllustrationPreview() {
    AndroidAppTemplateTheme {
        Illustration(
            state = Illustrations.FamilyBeachSunset.state,
            modifier = Modifier,
        )
    }
}
