package com.sottti.android.app.template.presentation.items.list.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.images.local.model.ImageState
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class ItemsListState(
    @StringRes val titleResId: Int,
    val items: Flow<PagingData<ItemState>>,
)

@Immutable
internal data class ItemState(
    val id: Int,
    val image: ItemImageState,
    val name: String,
)

@Immutable
internal sealed interface ItemImageState {
    @Immutable
    data class NetworkImage(
        val description: ImageContentDescription,
        val url: ImageUrl,
    ) : ItemImageState

    @Immutable
    data class PlaceholderImage(
        val state: ImageState,
    ) : ItemImageState
}
