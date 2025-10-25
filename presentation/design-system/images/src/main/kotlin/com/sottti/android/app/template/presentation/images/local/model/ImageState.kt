package com.sottti.android.app.template.presentation.images.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
public data class ImageState(
    @DrawableRes val resId: Int,
    @StringRes val descriptionResId: Int,
)
