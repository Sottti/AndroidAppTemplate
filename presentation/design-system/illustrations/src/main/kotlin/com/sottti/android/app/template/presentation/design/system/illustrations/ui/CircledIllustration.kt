package com.sottti.android.app.template.presentation.design.system.illustrations.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.illustrations.data.Illustrations
import com.sottti.android.app.template.presentation.design.system.illustrations.model.IllustrationState
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview

@Composable
public fun CircledIllustration(
    state: IllustrationState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = CircleShape,
    ) {
        Illustration(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.spacing.extraSmall)
        )
    }
}

@Composable
@AndroidAppTemplatePreview
internal fun CircledIllustrationPreview() {
    AndroidAppTemplateTheme {
        CircledIllustration(
            state = Illustrations.FamilyBeachSunset.state,
            modifier = Modifier,
        )
    }
}
