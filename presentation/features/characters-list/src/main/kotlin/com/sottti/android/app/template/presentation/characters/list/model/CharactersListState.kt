package com.sottti.android.app.template.presentation.characters.list.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.presentation.design.system.images.local.model.ImageState
import kotlinx.coroutines.flow.Flow

@Immutable
internal data class CharactersListState(
    @StringRes val titleResId: Int,
    val characters: Flow<PagingData<CharacterState>>,
)

@Immutable
internal data class CharacterState(
    val id: Int,
    val image: CharacterImageState,
    val name: String,
)

@Immutable
internal sealed interface CharacterImageState {
    @Immutable
    data class NetworkImage(
        val description: ImageContentDescription,
        val url: ImageUrl,
    ) : CharacterImageState

    @Immutable
    data class PlaceholderImage(
        val state: ImageState,
    ) : CharacterImageState
}
