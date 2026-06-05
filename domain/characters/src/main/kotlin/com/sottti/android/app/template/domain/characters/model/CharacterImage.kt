package com.sottti.android.app.template.domain.characters.model

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl

public data class CharacterImage(
    val description: ImageContentDescription,
    val url: ImageUrl,
)
