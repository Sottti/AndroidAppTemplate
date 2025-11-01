package com.sottti.android.app.template.data.items.datasource.remote.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.annotation.UnsafeResultErrorAccess
import com.github.michaelbull.result.annotation.UnsafeResultValueAccess
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.model.ItemId

internal class ItemsApiCallsFake : ItemsApiCalls {

    private var response: ResultApiModel<List<ItemApiModel>>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null
    var lastCalledPageSize: PageSizeApiModel? = null

    fun setSuccessResponse(items: List<ItemApiModel>) {
        response = Ok(items)
    }

    fun setErrorResponse(exception: Throwable) {
        response = Err(Unknown(exception.message ?: "Unknown"))
    }

    @OptIn(UnsafeResultValueAccess::class, UnsafeResultErrorAccess::class)
    override suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel> {
        val listResult = response
        return when {
            listResult == null -> throw IllegalStateException("Test response was not set in fake")
            listResult.isOk -> {
                val item = listResult.value.firstOrNull { it.id == itemId.value }
                    ?: return Err(Unknown("Item with id ${itemId.value} not found"))
                Ok(item)
            }

            listResult.isErr -> Err(listResult.error)
            else -> throw IllegalStateException("Test response was not set in fake")
        }
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
