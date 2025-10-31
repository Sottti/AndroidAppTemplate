package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.model.ItemId

internal interface ItemsApiCalls {
    suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel>
    suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): ResultApiModel<List<ItemApiModel>>
}
