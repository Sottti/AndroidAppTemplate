package com.sottti.android.app.template.data.items.datasource.model

import com.sottti.android.app.template.model.Item

internal data class PaginatedItems(
    val items: List<Item>,
    val nextPage: Int?,
)
