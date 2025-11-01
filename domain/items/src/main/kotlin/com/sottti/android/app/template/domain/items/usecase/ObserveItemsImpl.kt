package com.sottti.android.app.template.domain.items.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveItemsImpl @Inject constructor(
    private val itemsRepository: ItemsRepository,
) : ObserveItems {
    @OptIn(ExperimentalCoroutinesApi::class)
    public override operator fun invoke(): Flow<PagingData<Item>> =
        itemsRepository.observeItems()
}
