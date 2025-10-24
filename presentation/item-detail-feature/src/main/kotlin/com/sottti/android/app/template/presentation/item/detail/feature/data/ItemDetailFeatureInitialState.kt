package com.sottti.android.app.template.presentation.item.detail.feature.data

import com.sottti.android.app.template.presentation.item.detail.feature.R
import com.sottti.android.app.template.presentation.item.detail.feature.model.ItemDetailFeatureState

internal fun initialState() = ItemDetailFeatureState(
    buttonTextResId = R.string.item_detail_feature_button_text,
    textResId = R.string.item_detail_feature_text,
)
