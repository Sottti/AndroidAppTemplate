package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.domain.items.model.ItemId

internal interface ItemsApiCalls {
    suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel>
    suspend fun getItems(pageNumber: PageNumberApiModel): ResultApiModel<ItemsApiModel>
}
