package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.model.Item
import javax.inject.Inject

internal class ItemsRemoteDataSourceImpl @Inject constructor(
    private val api: ItemsApiCalls,
) : ItemsRemoteDataSource {

    override suspend fun getItems(): Result<List<Item>> =
        api
            .getItems()
            .mapBoth(
                success = { itemsApiModel -> Ok(itemsApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )
}
