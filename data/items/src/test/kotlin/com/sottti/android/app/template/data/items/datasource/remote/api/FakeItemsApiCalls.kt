package com.sottti.android.app.template.data.items.datasource.remote.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel

internal class FakeItemsApiCalls : ItemsApiCalls {

    private var response: ResultApiModel<List<ItemApiModel>>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null
    var lastCalledPageSize: PageSizeApiModel? = null

    fun setSuccessResponse(items: List<ItemApiModel>) {
        response = Ok(items)
    }

    fun setErrorResponse(exception: Throwable) {
        response = Err(ExceptionApiModel.Unknown(exception.message ?: "Unknown"))
    }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): ResultApiModel<List<ItemApiModel>> {
        lastCalledPageNumber = pageNumber
        lastCalledPageSize = pageSize

        return response ?: throw IllegalStateException("Test response was not set in fake")
    }
}
