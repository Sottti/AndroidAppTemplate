package com.sottti.android.app.template.domain.characters.usecase

import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow

public fun interface ObserveCharacter {
    public operator fun invoke(characterId: CharacterId): Flow<Character>
}
