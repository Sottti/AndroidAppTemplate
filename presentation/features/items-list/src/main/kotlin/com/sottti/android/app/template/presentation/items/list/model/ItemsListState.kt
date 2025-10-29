package com.sottti.android.app.template.presentation.items.list.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ItemsListState(
    @StringRes val titleResId: Int,
    val items: Flow<PagingData<ItemUiModel>>,
)

@Immutable
internal data class ItemUiModel(
    val description: ImageContentDescription,
    val id: String,
    val imageUrl: ImageUrl,
    val name: String,
)
