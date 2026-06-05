package com.sottti.android.app.template.domain.characters.repository

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow

public interface CharactersRepository {
    public fun observeCharacter(characterId: CharacterId): Flow<Character>
    public fun observeCharacters(): Flow<PagingData<Character>>
}
