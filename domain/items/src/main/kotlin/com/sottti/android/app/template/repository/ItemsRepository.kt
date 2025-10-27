package com.sottti.android.app.template.repository

import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.flow.Flow

public fun interface ItemsRepository {
    public fun observeItems(): Flow<PagingData<Item>>
}
