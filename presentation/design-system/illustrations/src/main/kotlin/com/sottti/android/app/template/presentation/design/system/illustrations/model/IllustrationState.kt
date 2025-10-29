package com.sottti.android.app.template.presentation.design.system.illustrations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
public data class IllustrationState(
    @DrawableRes val resId: Int,
    @StringRes val descriptionResId: Int,
)
