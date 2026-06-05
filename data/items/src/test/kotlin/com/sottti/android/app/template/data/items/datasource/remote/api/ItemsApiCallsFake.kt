package com.sottti.android.app.template.data.items.datasource.remote.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.annotation.UnsafeResultErrorAccess
import com.github.michaelbull.result.annotation.UnsafeResultValueAccess
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageInfoApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.domain.items.model.ItemId

internal class ItemsApiCallsFake : ItemsApiCalls {

    private var response: ResultApiModel<ItemsApiModel>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null

    fun setSuccessResponse(items: List<ItemApiModel>) {
        response = Ok(
            ItemsApiModel(
                info = PageInfoApiModel(
                    count = items.size,
                    pages = 1,
                    next = null,
                    prev = null,
                ),
                results = items,
            )
        )
    }

    fun setErrorResponse(exception: Throwable) {
        response = Err(Unknown(exception.message ?: "Unknown"))
    }

    @OptIn(UnsafeResultValueAccess::class, UnsafeResultErrorAccess::class)
    override suspend fun getItem(itemId: ItemId): ResultApiModel<ItemApiModel> {
        val listResult = checkNotNull(response) { "Test response was not set in fake" }
        return when {
            listResult.isOk -> {
                val item = listResult.value.results.firstOrNull { it.id == itemId.value }
                    ?: return Err(Unknown("Item with id ${itemId.value} not found"))
                Ok(item)
            }

            listResult.isErr -> Err(listResult.error)
            else -> error("Test response was not set in fake")
        }
    }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
    ): ResultApiModel<ItemsApiModel> {
        lastCalledPageNumber = pageNumber
        return response ?: error("Test response was not set in fake")
    }
}
