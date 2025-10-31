package com.sottti.android.app.template.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.flow.Flow

public interface ObserveItems {
    public operator fun invoke(): Flow<PagingData<Item>>
}
