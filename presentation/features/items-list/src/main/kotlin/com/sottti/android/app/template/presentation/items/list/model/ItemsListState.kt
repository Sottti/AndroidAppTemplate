package com.sottti.android.app.template.presentation.items.list.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ItemsListState(
    @StringRes val titleResId: Int,
    val items: Flow<PagingData<Item>>,
)
