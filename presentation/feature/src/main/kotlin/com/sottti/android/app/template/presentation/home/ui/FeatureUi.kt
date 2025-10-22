package com.sottti.android.app.template.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sottti.android.app.template.presentation.design.system.colors.color.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.dimensions
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.home.model.FeatureActions
import com.sottti.android.app.template.presentation.home.model.FeatureState

@Composable
internal fun HomeUi(
    state: FeatureState,
    onAction: (FeatureActions) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text.Headline.Medium(textResId = state.textResId, textColor = colors.onBackground)
        Spacer(modifier = Modifier.height(dimensions.spacing.medium))
        Button(onClick = { onAction(FeatureActions.NoOp) }) {
            Text.Body.Large(textResId = state.buttonTextResId)
        }
    }
}
