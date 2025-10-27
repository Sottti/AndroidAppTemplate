package com.sottti.android.app.template.model

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl

public data class ItemImage(
    val description: ImageContentDescription,
    val imageUrl: ImageUrl,
)
