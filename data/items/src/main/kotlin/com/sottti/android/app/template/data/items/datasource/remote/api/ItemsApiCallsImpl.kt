package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.API_PATH_BEERS
import com.sottti.android.app.template.data.network.API_QUERY_PARAM_ITEMS_PER_PAGE
import com.sottti.android.app.template.data.network.API_QUERY_PARAM_PAGE_NUMBER
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import com.sottti.android.app.template.domain.items.model.ItemId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject

internal class ItemsApiCallsImpl @Inject constructor(
    private val httpClient: HttpClient,
) : ItemsApiCalls {
    override suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel> =
        safeApiCall {
            httpClient.get {
                url { path(API_PATH_BEERS, itemId.value.toString()) }
            }.body()
        }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): ResultApiModel<List<ItemApiModel>> =
        safeApiCall {
            httpClient.get(API_PATH_BEERS) {
                parameter(API_QUERY_PARAM_PAGE_NUMBER, pageNumber.value)
                parameter(API_QUERY_PARAM_ITEMS_PER_PAGE, pageSize.value)
            }.body()
        }
}
