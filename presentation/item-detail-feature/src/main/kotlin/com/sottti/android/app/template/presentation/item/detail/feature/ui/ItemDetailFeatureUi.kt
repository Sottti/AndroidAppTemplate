package com.sottti.android.app.template.presentation.item.detail.feature.ui

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
import com.sottti.android.app.template.presentation.item.detail.feature.data.ItemDetailFeatureViewModel
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureActions
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureState

@Composable
public fun ItemDetailUi() {
    ItemDetailUi(hiltViewModel<ItemDetailFeatureViewModel>())
}

@Composable
internal fun ItemDetailUi(
    viewModel: ItemDetailFeatureViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    FeatureUi(
        state = state,
        onAction = viewModel.onAction,
    )
}

@Composable
internal fun FeatureUi(
    state: ItemDetailFeatureState,
    onAction: (ItemDetailFeatureActions) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text.Headline.Medium(textResId = state.textResId, textColor = colors.onBackground)
        Spacer(modifier = Modifier.height(dimensions.spacing.medium))
        Button(onClick = { onAction(ItemDetailFeatureActions.NavigateBack) }) {
            Text.Body.Large(textResId = state.buttonTextResId)
        }
    }
}
