package com.sottti.android.app.template.domain.characters.usecase

import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

public class ObserveCharacterFake : ObserveCharacter {
    private var characterFlow: Flow<Character> = emptyFlow()
    private var lastCalledCharacterId: CharacterId? = null

    public fun setCharacterFlow(flow: Flow<Character>) {
        this.characterFlow = flow
    }

    override operator fun invoke(characterId: CharacterId): Flow<Character> {
        lastCalledCharacterId = characterId
        return characterFlow
    }
}
