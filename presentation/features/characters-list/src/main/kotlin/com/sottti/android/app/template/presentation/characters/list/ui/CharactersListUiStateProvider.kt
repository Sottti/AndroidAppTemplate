package com.sottti.android.app.template.presentation.characters.list.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.sottti.android.app.template.presentation.characters.list.R
import com.sottti.android.app.template.presentation.characters.list.fixtures.listOfMultipleCharactersUiModels
import com.sottti.android.app.template.presentation.characters.list.fixtures.listOfTwoCharactersUiModels
import com.sottti.android.app.template.presentation.characters.list.model.CharacterState
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class CharactersListUiStateProvider : PreviewParameterProvider<CharactersListState> {
    override val values: Sequence<CharactersListState>
        get() = sequence {
            yield(loadingState)
            yield(loadedStatePrependLoading)
            yield(loadedStateAppendLoading)
            yield(loadedStateAppendPrependBothLoading)
            yield(loadedStateNoPagination)
            yield(loadedStateAppendEndReached)
            yield(loadedStatePrependEndReached)
            yield(loadedStateAppendPrependBothEndsReached)
            yield(loadedStateLoadsOfCharacters)
            yield(loadedStateLoadsOfCharactersRefreshing)
            yield(emptyState)
            yield(errorState)
            yield(loadedStateAppendError)
            yield(loadedStatePrependError)
        }
}

internal val loadingState = charactersListState(
    refreshState = Loading,
    data = emptyList(),
)
internal val loadedStateAppendLoading = charactersListState(appendState = Loading)
internal val loadedStatePrependLoading = charactersListState(prependState = Loading)
internal val loadedStateAppendPrependBothLoading =
    charactersListState(appendState = Loading, prependState = Loading)
internal val loadedStateNoPagination = charactersListState()
internal val loadedStateAppendEndReached = charactersListState(
    appendState = NotLoading(endOfPaginationReached = true),
)
internal val loadedStatePrependEndReached = charactersListState(
    prependState = NotLoading(endOfPaginationReached = true)
)
internal val loadedStateAppendPrependBothEndsReached = charactersListState(
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true)
)

internal val loadedStateLoadsOfCharacters = charactersListState(
    appendState = NotLoading(endOfPaginationReached = true),
    data = listOfMultipleCharactersUiModels,
)

internal val loadedStateLoadsOfCharactersRefreshing = charactersListState(
    refreshState = Loading,
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = listOfMultipleCharactersUiModels,
)

internal val emptyState = charactersListState(
    refreshState = NotLoading(endOfPaginationReached = true),
    appendState = NotLoading(endOfPaginationReached = true),
    prependState = NotLoading(endOfPaginationReached = true),
    data = emptyList(),
)

internal val errorState = charactersListState(
    refreshState = LoadState.Error(kotlin.Exception("Refresh failed.")),
    data = emptyList(),
)

internal val loadedStateAppendError = charactersListState(
    appendState = LoadState.Error(kotlin.Exception("Append failed.")),
)

internal val loadedStatePrependError = charactersListState(
    prependState = LoadState.Error(kotlin.Exception("Prepend failed.")),
)

@OptIn(ExperimentalMaterial3Api::class)
private fun charactersListState(
    refreshState: LoadState = NotLoading(endOfPaginationReached = false),
    appendState: LoadState = NotLoading(endOfPaginationReached = false),
    prependState: LoadState = NotLoading(endOfPaginationReached = false),
    data: List<CharacterState> = listOfTwoCharactersUiModels,
) = CharactersListState(
    titleResId = R.string.characters_list_title,
    characters = charactersPagingDataFlow(
        appendState = appendState,
        data = data,
        prependState = prependState,
        refreshState = refreshState,
    ),
)

private fun charactersPagingDataFlow(
    refreshState: LoadState,
    appendState: LoadState,
    prependState: LoadState,
    data: List<CharacterState>,
): Flow<PagingData<CharacterState>> =
    MutableStateFlow(
        PagingData.from(
            data = data,
            sourceLoadStates = LoadStates(
                refresh = refreshState,
                prepend = prependState,
                append = appendState,
            )
        )
    )
