package com.sottti.android.app.template.domain.items.usecase

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import kotlinx.coroutines.flow.Flow

public fun interface ObserveItem {
    public operator fun invoke(itemId: ItemId): Flow<Item>
}
