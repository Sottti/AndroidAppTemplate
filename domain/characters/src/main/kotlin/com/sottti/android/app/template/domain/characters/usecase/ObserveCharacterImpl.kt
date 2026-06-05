package com.sottti.android.app.template.domain.characters.usecase

import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.domain.characters.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveCharacterImpl @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ObserveCharacter {
    @OptIn(ExperimentalCoroutinesApi::class)
    public override operator fun invoke(characterId: CharacterId): Flow<Character> =
        charactersRepository.observeCharacter(characterId = characterId)
}
