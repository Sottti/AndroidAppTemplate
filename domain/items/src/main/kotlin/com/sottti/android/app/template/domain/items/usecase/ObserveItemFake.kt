package com.sottti.android.app.template.domain.items.usecase

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

public class ObserveItemFake : ObserveItem {
    private var itemFlow: Flow<Item> = emptyFlow()
    private var lastCalledItemId: ItemId? = null

    public fun setItemFlow(flow: Flow<Item>) {
        this.itemFlow = flow
    }

    override operator fun invoke(itemId: ItemId): Flow<Item> {
        lastCalledItemId = itemId
        return itemFlow
    }
}
