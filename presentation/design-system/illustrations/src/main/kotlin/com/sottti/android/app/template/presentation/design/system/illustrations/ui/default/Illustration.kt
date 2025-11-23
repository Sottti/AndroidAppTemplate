package com.sottti.android.app.template.presentation.design.system.illustrations.ui.default

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.PreviewAndroidAppTemplate
import androidx.compose.foundation.Image as MaterialImage

@Composable
public fun Illustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
    circled: Boolean = false,
) {
    val modifier: Modifier =
        when {
            circled ->
                modifier
                    .aspectRatio(1f)
                    .clip(CircleShape)

            else -> modifier
        }
    MaterialImage(
        contentDescription = stringResource(state.descriptionResId),
        contentScale = ContentScale.Crop,
        modifier = modifier,
        painter = painterResource(id = state.resId),
    )
}

@Composable
@PreviewAndroidAppTemplate
internal fun IllustrationPreview(
    @PreviewParameter(IllustrationUiStateProvider::class)
    state: IllustrationPreviewState,
) {
    AndroidAppTemplateTheme {
        Illustration(
            circled = state.circled,
            modifier = state.modifier,
            state = state.state,
        )
    }
}
