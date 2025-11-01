package com.sottti.android.app.template.domain.items.repository

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import kotlinx.coroutines.flow.Flow

public interface ItemsRepository {
    public fun observeItem(itemId: ItemId): Flow<Item>
    public fun observeItems(): Flow<PagingData<Item>>
}
