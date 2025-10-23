package com.sottti.android.app.template.domain.cores.models

import androidx.annotation.ChecksSdkIntAtLeast

public data class DynamicColor(
    @ChecksSdkIntAtLeast(api = 31) val enabled: Boolean,
)
