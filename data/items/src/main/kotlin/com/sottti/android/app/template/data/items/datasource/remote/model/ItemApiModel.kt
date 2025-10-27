package com.sottti.android.app.template.data.items.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemApiModel(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("imageDescription")
    val imageDescription: String,
)
