package com.sottti.android.app.template.domain.items.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.items.model.Item
import kotlinx.coroutines.flow.Flow

public fun interface ObserveItems {
    public operator fun invoke(): Flow<PagingData<Item>>
}
