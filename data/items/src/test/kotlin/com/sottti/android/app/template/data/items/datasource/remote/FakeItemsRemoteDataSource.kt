package com.sottti.android.app.template.data.items.datasource.remote

import com.sottti.android.app.template.data.items.datasource.model.PaginatedItems
import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.domain.core.models.Result

private typealias ItemsResponseProvider =
        suspend (page: PageApiModel, pageSize: PageSizeApiModel) -> Result<PaginatedItems>

internal class FakeItemsRemoteDataSource(
    private val responder: ItemsResponseProvider,
) : ItemsRemoteDataSource {
    override suspend fun getItems(
        page: PageApiModel,
        pageSize: PageSizeApiModel,
    ): Result<PaginatedItems> = responder(page, pageSize)
}
