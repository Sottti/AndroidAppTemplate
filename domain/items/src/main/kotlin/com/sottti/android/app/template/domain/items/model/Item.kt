package com.sottti.android.app.template.domain.items.model

public data class Item(
    val id: ItemId,
    val image: ItemImage,
    val name: ItemName,
    val tagline: ItemDescription,
    val year: ItemYear,
)
