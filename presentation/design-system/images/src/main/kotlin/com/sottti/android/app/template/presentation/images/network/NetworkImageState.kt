package com.sottti.android.app.template.presentation.images.network

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl

@Immutable
internal data class NetworkImageState(
    val contentDescription: ImageContentDescription,
    val foreverLoading: Boolean,
    val imageUrl: ImageUrl,
    val roundedCorners: Boolean,
)
