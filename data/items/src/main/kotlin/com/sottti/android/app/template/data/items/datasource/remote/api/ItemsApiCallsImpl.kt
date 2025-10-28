package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.network.data.API_PATH_ITEMS
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class ItemsApiCallsImpl @Inject constructor(
    private val httpClient: HttpClient,
) : ItemsApiCalls {
    override suspend fun getItems(): ResultApiModel<ItemsApiModel> =
        safeApiCall { httpClient.get(API_PATH_ITEMS).body() }
}
