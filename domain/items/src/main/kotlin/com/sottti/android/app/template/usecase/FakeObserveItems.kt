package com.sottti.android.app.template.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

public class FakeObserveItems : ObserveItems {

    private var itemsFlow: Flow<PagingData<Item>> = flowOf(PagingData.empty())

    public fun setItems(flow: Flow<PagingData<Item>>) {
        itemsFlow = flow
    }

    override operator fun invoke(): Flow<PagingData<Item>> = itemsFlow
}
