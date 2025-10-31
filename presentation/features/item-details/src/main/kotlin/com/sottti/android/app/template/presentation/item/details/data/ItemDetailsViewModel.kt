package com.sottti.android.app.template.presentation.item.details.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsActions.NavigateBack
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.utils.stateInWhileSubscribed
import com.sottti.android.app.template.usecase.ObserveItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan

@HiltViewModel(assistedFactory = ItemDetailsViewModel.Factory::class)
internal class ItemDetailsViewModel @AssistedInject constructor(
    @Assisted val itemId: ItemId,
    private val navigationManager: NavigationManager,
    private val observeItem: ObserveItem,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(id: ItemId): ItemDetailsViewModel
    }

    val state: StateFlow<ItemDetailsState> =
        observeItem(itemId)
            .map { item -> reducer(item) }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .stateInWhileSubscribed(initialState)

    internal val onAction: (ItemDetailsActions) -> Unit = ::processAction
    private fun processAction(action: ItemDetailsActions) =
        when (action) {
            NavigateBack -> navigationManager.navigateBack()
        }

    private val reducer: (
        item: Item,
    ) -> (ItemDetailsState) -> ItemDetailsState =
        { item ->
            { previous: ItemDetailsState ->
                ItemDetailsState.Loaded(
                    imageDescription = item.image.description.value,
                    imageUrl = item.image.imageUrl,
                    name = item.name.value,
                    topBarState = previous.topBarState.copy(title = item.name.value),
                )
            }
        }
}
