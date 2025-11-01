package com.sottti.android.app.template.domain.items.usecase

import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import com.sottti.android.app.template.domain.items.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveItemImpl @Inject constructor(
    private val itemsRepository: ItemsRepository,
) : ObserveItem {
    @OptIn(ExperimentalCoroutinesApi::class)
    public override operator fun invoke(itemId: ItemId): Flow<Item> =
        itemsRepository.observeItem(itemId = itemId)
}
