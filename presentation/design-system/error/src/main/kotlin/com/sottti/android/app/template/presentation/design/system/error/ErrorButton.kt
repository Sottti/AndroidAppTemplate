package com.sottti.android.app.template.presentation.design.system.error

import androidx.annotation.StringRes

public data class ErrorButton(
    @StringRes val text: Int = R.string.error_button_text_default,
    val onClick: () -> Unit,
)
