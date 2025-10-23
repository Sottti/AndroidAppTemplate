package com.sottti.android.app.template.presentation.feature.data

import com.sottti.android.app.template.presentation.feature.R
import com.sottti.android.app.template.presentation.feature.model.FeatureState

internal fun initialState() = FeatureState(
    buttonTextResId = R.string.feature_button_text,
    textResId = R.string.feature_text,
)
