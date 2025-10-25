package com.sottti.android.app.template.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveItems @Inject constructor(
    private val itemsRepository: ItemsRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public operator fun invoke(): Flow<PagingData<Item>> =
        itemsRepository.observeItems()
}
