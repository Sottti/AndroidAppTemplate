package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel

internal fun interface ItemsApiCalls {
    suspend fun getItems(): ResultApiModel<ItemsApiModel>
}
