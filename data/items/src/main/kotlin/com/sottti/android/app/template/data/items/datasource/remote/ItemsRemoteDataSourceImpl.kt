package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.sottti.android.app.template.data.items.datasource.model.PaginatedItems
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.domain.core.models.Result
import javax.inject.Inject

internal class ItemsRemoteDataSourceImpl @Inject constructor(
    private val api: ItemsApiCalls,
) : ItemsRemoteDataSource {

    override suspend fun getItems(
        page: PageApiModel,
        pageSize: PageSizeApiModel,
    ): Result<PaginatedItems> =
        api
            .getItems(page = page, size = pageSize)
            .mapBoth(
                success = { itemsApiModel -> Ok(itemsApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )
}
