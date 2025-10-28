package com.sottti.android.app.template.data.items.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemsApiModel(
    @SerialName("items")
    val items: List<ItemApiModel>,
)
