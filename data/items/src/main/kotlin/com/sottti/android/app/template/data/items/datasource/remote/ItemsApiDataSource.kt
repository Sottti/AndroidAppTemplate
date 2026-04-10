package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import javax.inject.Inject

internal class ItemsApiDataSource @Inject constructor(
    private val api: ItemsApiCalls,
) : ItemsRemoteDataSource {
    override suspend fun getItem(itemId: ItemId): Result<Item> =
        api
            .getItem(itemId)
            .mapBoth(
                success = { itemApiModel -> Ok(itemApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )

    override suspend fun getItems(
        pageNumber: PageNumberApiModel,
    ): Result<List<Item>> =
        api
            .getItems(pageNumber = pageNumber)
            .mapBoth(
                success = { itemsApiModel -> Ok(itemsApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )
}
