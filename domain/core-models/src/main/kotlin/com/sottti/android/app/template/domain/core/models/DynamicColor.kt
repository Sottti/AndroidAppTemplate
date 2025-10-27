package com.sottti.android.app.template.domain.core.models

import androidx.annotation.ChecksSdkIntAtLeast

public data class DynamicColor(
    @ChecksSdkIntAtLeast(api = 31) val enabled: Boolean,
)
