package com.sottti.android.app.template.usecase

import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import kotlinx.coroutines.flow.Flow

public interface ObserveItem {
    public operator fun invoke(itemId: ItemId): Flow<Item>
}
