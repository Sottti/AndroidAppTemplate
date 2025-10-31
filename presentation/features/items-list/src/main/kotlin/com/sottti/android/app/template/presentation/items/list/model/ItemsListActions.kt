package com.sottti.android.app.template.presentation.items.list.model

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface ItemsListActions {
    @Immutable
    data class ShowItemDetail(val itemId: Int) : ItemsListActions
}
