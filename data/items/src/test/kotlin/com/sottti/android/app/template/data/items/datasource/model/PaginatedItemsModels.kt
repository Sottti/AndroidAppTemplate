package com.sottti.android.app.template.data.items.datasource.model

import com.sottti.android.app.template.data.items.datasource.data.item
import com.sottti.android.app.template.data.items.datasource.data.item2
import com.sottti.android.app.template.data.items.datasource.remote.model.paginatedItemsApiModel

internal val paginatedItemsModel =
    PaginatedItems(
        items = listOf(item, item2),
        nextPage = paginatedItemsApiModel.nextPage,
    )
