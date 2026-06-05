package com.sottti.android.app.template.data.characters.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.characters.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow

internal interface CharactersLocalDataSource {
    fun observeCharacter(characterId: CharacterId): Flow<Character?>
    fun observeCharacters(): PagingSource<Int, Character>
    suspend fun isExpired(characterId: CharacterId): Boolean
    suspend fun clearAll()
    suspend fun clearAndInsert(characters: List<Character>)
    suspend fun getNextRemotePage(): RemoteKeysRoomModel?
    suspend fun runTransaction(block: suspend () -> Unit)
    suspend fun updateRemoteKeys(nextPage: Int?, prevPage: Int?)
    suspend fun upsert(character: Character)
    suspend fun upsert(characters: List<Character>)
}
