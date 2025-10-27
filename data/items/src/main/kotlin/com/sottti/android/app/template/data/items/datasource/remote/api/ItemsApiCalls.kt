package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PaginatedItemsApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel

internal fun interface ItemsApiCalls {
    suspend fun getItems(
        page: PageApiModel,
        size: PageSizeApiModel,
    ): ResultApiModel<PaginatedItemsApiModel>
}
