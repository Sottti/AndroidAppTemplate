package com.sottti.android.app.template.data.items.datasource.remote

import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.model.Item

internal fun interface ItemsRemoteDataSource {
    suspend fun getItems(): Result<List<Item>>
}
