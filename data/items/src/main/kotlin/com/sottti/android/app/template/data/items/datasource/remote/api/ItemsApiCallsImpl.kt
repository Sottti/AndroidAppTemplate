package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PaginatedItemsApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class ItemsApiCallsImpl @Inject constructor(
    private val httpClient: HttpClient,
) : ItemsApiCalls {
    override suspend fun getItems(
        page: PageApiModel,
        size: PageSizeApiModel,
    ): ResultApiModel<PaginatedItemsApiModel> =
        safeApiCall {
            httpClient.get(API_PATH_ITEMS) {
                parameter(API_QUERY_PARAM_PAGE, page.value)
                parameter(API_QUERY_PARAM_SIZE, size.value)
            }.body()
        }
}
