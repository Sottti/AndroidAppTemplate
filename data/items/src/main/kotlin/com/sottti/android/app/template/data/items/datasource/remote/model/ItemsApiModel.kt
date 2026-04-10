package com.sottti.android.app.template.data.items.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ItemsApiModel(
    @SerialName("info")
    val info: PageInfoApiModel,
    @SerialName("results")
    val results: List<ItemApiModel>
)
