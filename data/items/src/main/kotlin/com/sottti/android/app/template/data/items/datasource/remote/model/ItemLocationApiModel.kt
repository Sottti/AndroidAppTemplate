package com.sottti.android.app.template.data.items.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemLocationApiModel(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
