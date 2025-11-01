package com.sottti.android.app.template.presentation.item.details.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState

@Immutable
internal sealed class ItemDetailsState {
    abstract val topBarState: TopBarState


    @Immutable
    data class Loading(
        override val topBarState: TopBarState,
    ) : ItemDetailsState()

    @Immutable
    data class Error(
        override val topBarState: TopBarState,
    ) : ItemDetailsState()

    @Immutable
    data class Loaded(
        override val topBarState: TopBarState,
        val item: ItemState,
    ) : ItemDetailsState()
}

@Immutable
internal data class TopBarState(
    val navigationIcon: IconState,
    val title: String?,
)

@Immutable
internal sealed interface ItemDetailsSectionState {
    @get:StringRes
    val header: Int
}

@Immutable
internal data class ItemState(
    val imageState: ImageState,
    val identity: ItemIdentityState,
)

@Immutable
internal data class ImageState(
    val imageDescription: ImageContentDescription,
    val imageUrl: ImageUrl,
)

@Immutable
internal data class ItemIdentityState(
    override val header: Int,
    val name: ItemDetailsRow,
    val description: ItemDetailsRow,
) : ItemDetailsSectionState

@Immutable
internal data class ItemDetailsRow(
    @StringRes val headline: Int,
    val trailing: String?,
)
