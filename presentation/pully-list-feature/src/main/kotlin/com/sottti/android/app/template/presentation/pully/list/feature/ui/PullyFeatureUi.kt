package com.sottti.android.app.template.presentation.pully.list.feature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal.dimensions
import com.sottti.android.app.template.presentation.design.system.text.Text
import com.sottti.android.app.template.presentation.pully.list.feature.data.PullyListFeatureViewModel
import com.sottti.android.app.template.presentation.pully.list.feature.model.PullyListFeatureActions
import com.sottti.android.app.template.presentation.pully.list.feature.model.PullyListFeatureState

@Composable
public fun PullyListFeatureUi() {
    PullyListFeatureUi(hiltViewModel<PullyListFeatureViewModel>())
}

@Composable
internal fun PullyListFeatureUi(
    viewModel: PullyListFeatureViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FeatureUi(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
internal fun FeatureUi(
    state: PullyListFeatureState,
    onAction: (PullyListFeatureActions) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text.Headline.Medium(textResId = state.textResId, textColor = colors.onBackground)
        Spacer(modifier = Modifier.height(dimensions.spacing.medium))
        Button(onClick = { onAction(PullyListFeatureActions.ShowDetail) }) {
            Text.Body.Large(textResId = state.buttonTextResId)
        }
    }
}
