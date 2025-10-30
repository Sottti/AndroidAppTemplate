package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.model.Item

internal class FakeItemsRemoteDataSource : ItemsRemoteDataSource {

    private var response: Result<List<Item>>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null
    var lastCalledPageSize: PageSizeApiModel? = null

    fun setSuccessResponse(items: List<Item>) {
        response = Ok(items)
    }

    fun setErrorResponse(exception: Exception) {
        response = Err(exception)
    }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): Result<List<Item>> {
        lastCalledPageNumber = pageNumber
        lastCalledPageSize = pageSize

        return response ?: throw IllegalStateException("Test response was not set in the fake")
    }
}
