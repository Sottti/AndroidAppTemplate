package com.sottti.android.app.template.usecase

import com.sottti.android.app.template.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

public class RefreshItemsIfNeeded @Inject constructor(
    private val itemsRepository: ItemsRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    public suspend operator fun invoke() {
        itemsRepository.refreshItemsIfNeeded()
    }
}
