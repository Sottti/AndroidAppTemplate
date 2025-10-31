package com.sottti.android.app.template.usecase

import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.repository.ItemsRepository
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
