package com.sottti.android.app.template.data.items.datasource.remote.api

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.API_PATH_CHARACTERS
import com.sottti.android.app.template.data.network.API_QUERY_PARAM_PAGE_NUMBER
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import com.sottti.android.app.template.domain.items.model.ItemId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import javax.inject.Inject

internal class ItemsApiCallsImpl @Inject constructor(
    private val httpClient: HttpClient,
) : ItemsApiCalls {

    override suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel> =
        safeApiCall {
            httpClient.get(API_PATH_CHARACTERS) {
                url { appendPathSegments(itemId.value.toString()) }
            }.body()
        }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
    ): ResultApiModel<ItemsApiModel> =
        safeApiCall {
            httpClient.get(API_PATH_CHARACTERS) {
                parameter(API_QUERY_PARAM_PAGE_NUMBER, pageNumber.value)
            }.body()
        }
}
