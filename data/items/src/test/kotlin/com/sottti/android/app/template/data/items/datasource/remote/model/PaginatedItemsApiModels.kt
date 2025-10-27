package com.sottti.android.app.template.data.items.datasource.remote.model

internal val paginatedItemsApiModel =
    PaginatedItemsApiModel(
        firstPage = 1,
        previousPage = null,
        nextPage = 2,
        lastPage = 10,
        totalPages = 10,
        totalItems = 100,
        items = listOf(itemApiModel, item2ApiModel)
    )
