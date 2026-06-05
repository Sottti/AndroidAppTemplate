package com.sottti.android.app.template.presentation.character.details.data

import androidx.lifecycle.ViewModel
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacter
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsActions
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsActions.NavigateBack
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.utils.stateInWhileSubscribed
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.scan

private typealias CharacterDetailsReducer = (CharacterDetailsState) -> CharacterDetailsState

@HiltViewModel(assistedFactory = CharacterDetailsViewModel.Factory::class)
internal class CharacterDetailsViewModel @AssistedInject constructor(
    @Assisted val characterId: CharacterId,
    private val navigationManager: NavigationManager,
    observeCharacter: ObserveCharacter,
) : ViewModel() {

    @AssistedFactory
    fun interface Factory {
        fun create(id: CharacterId): CharacterDetailsViewModel
    }

    val state: StateFlow<CharacterDetailsState> =
        observeCharacter(characterId)
            .map { character -> reducer(character) }
            .scan(initialState) { previous, reduce -> reduce(previous) }
            .stateInWhileSubscribed(initialState)

    internal val onAction: (CharacterDetailsActions) -> Unit = ::processAction
    private fun processAction(action: CharacterDetailsActions) =
        when (action) {
            NavigateBack -> navigationManager.navigateBack()
            CharacterDetailsActions.Retry -> {}
        }

    private val reducer: (character: Character) -> CharacterDetailsReducer =
        { character -> { previous: CharacterDetailsState -> previous.reduce(update = character) } }
}
