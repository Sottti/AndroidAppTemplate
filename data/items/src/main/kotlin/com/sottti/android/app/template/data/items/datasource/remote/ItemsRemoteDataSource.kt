package com.sottti.android.app.template.data.items.datasource.remote

import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId

internal interface ItemsRemoteDataSource {
    suspend fun getItem(itemId: ItemId): Result<Item>
    suspend fun getItems(
        pageNumber: PageNumberApiModel,
        pageSize: PageSizeApiModel,
    ): Result<List<Item>>
}
