package com.sottti.android.app.template.presentation.characters.list.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacters
import com.sottti.android.app.template.presentation.characters.list.R
import com.sottti.android.app.template.presentation.characters.list.model.CharacterState
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListActions.ShowCharacterDetail
import com.sottti.android.app.template.presentation.characters.list.model.CharactersListState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.CharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class CharactersListViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    observeCharacters: ObserveCharacters,
    testScope: CoroutineScope? = null,
) : ViewModel() {

    private val characters: Flow<PagingData<CharacterState>> =
        observeCharacters()
            .map { pagingData -> pagingData.map { character -> character.toUi() } }
            .let { flow -> if (testScope == null) flow.cachedIn(viewModelScope) else flow }

    val state: StateFlow<CharactersListState> =
        MutableStateFlow(
            CharactersListState(
                titleResId = R.string.characters_list_title,
                characters = characters,
            )
        ).asStateFlow()

    internal val onAction: (CharactersListActions) -> Unit = ::processAction
    private fun processAction(
        action: CharactersListActions,
    ) = when (action) {
        is ShowCharacterDetail -> navigationManager.navigateTo(CharacterDetail(action.characterId))
    }
}
