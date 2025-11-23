package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.annotation.UnsafeResultErrorAccess
import com.github.michaelbull.result.annotation.UnsafeResultValueAccess
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId

internal class ItemsRemoteDataSourceFake : ItemsRemoteDataSource {

    private var response: Result<List<Item>>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null
    var lastCalledPageSize: PageSizeApiModel? = null

    fun setSuccessResponse(items: List<Item>) {
        response = Ok(items)
    }

    fun setErrorResponse(exception: Exception) {
        response = Err(exception)
    }

    @OptIn(UnsafeResultValueAccess::class, UnsafeResultErrorAccess::class)
    override suspend fun getItem(itemId: ItemId): Result<Item> {
        val listResult = checkNotNull(response) { "Test response was not set in fake" }

        return when {
            listResult.isOk -> {
                val item = listResult.value.firstOrNull { it.id.value == itemId.value }
                    ?: return Err(Unknown("Item with id ${itemId.value} not found"))
                Ok(item)
            }

            listResult.isErr -> Err(listResult.error)
            else -> error("Unexpected result state")
        }
    }

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): Result<List<Item>> {
        lastCalledPageNumber = pageNumber
        lastCalledPageSize = pageSize

        return checkNotNull(response) { "Test response was not set in the fake" }
    }
}
