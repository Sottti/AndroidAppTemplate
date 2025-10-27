package com.sottti.android.app.template.data.items.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginatedItemsApiModel(
    @SerialName("first")
    val firstPage: Int,

    @SerialName("prev")
    val previousPage: Int?,

    @SerialName("next")
    val nextPage: Int?,

    @SerialName("last")
    val lastPage: Int,

    @SerialName("pages")
    val totalPages: Int,

    @SerialName("items")
    val totalItems: Int,

    @SerialName("data")
    val items: List<ItemApiModel>,
)
