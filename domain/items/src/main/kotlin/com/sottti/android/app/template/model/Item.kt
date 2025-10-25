package com.sottti.android.app.template.model

import com.sottti.android.app.template.domain.cores.models.ImageContentDescription
import com.sottti.android.app.template.domain.cores.models.ImageUrl

public data class Item(
    val id: ItemId,
    val name: ItemName,
    val description: ImageContentDescription,
    val imageUrl: ImageUrl,
)
