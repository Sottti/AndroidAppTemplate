package com.sottti.android.app.template.data.characters.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.characters.datasource.local.database.CharactersDao
import com.sottti.android.app.template.data.characters.datasource.local.database.RemoteKeysDao
import com.sottti.android.app.template.data.characters.datasource.local.mapper.CharacterMappingPagingSource
import com.sottti.android.app.template.data.characters.datasource.local.mapper.toDomain
import com.sottti.android.app.template.data.characters.datasource.local.mapper.toRoom
import com.sottti.android.app.template.data.characters.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CharactersRoomDataSource @Inject constructor(
    private val charactersDao: CharactersDao,
    private val remoteKeysDao: RemoteKeysDao,
    private val timeProvider: TimeProvider,
) : CharactersLocalDataSource {
    override fun observeCharacter(characterId: CharacterId): Flow<Character?> =
        charactersDao
            .observeCharacter(characterId.value)
            .map { characterRoomModel -> characterRoomModel?.toDomain() }

    override fun observeCharacters(): PagingSource<Int, Character> =
        CharacterMappingPagingSource(roomPagingSource = charactersDao.observeCharacters())

    override suspend fun isExpired(characterId: CharacterId): Boolean =
        charactersDao
            .getCharacter(characterId.value)
            ?.isExpired(timeProvider.now())
            ?: true

    override suspend fun upsert(character: Character) {
        charactersDao.upsert(character.toRoom(timeProvider.now()))
    }

    override suspend fun upsert(characters: List<Character>) {
        charactersDao.upsert(characters = characters.toRoom(timeProvider.now()))
    }

    override suspend fun clearAndInsert(
        characters: List<Character>,
    ) {
        charactersDao.clear()
        charactersDao.upsert(characters = characters.toRoom(timeProvider.now()))
    }

    override suspend fun clearAll() {
        charactersDao.clear()
        remoteKeysDao.clear()
    }

    override suspend fun getNextRemotePage() =
        remoteKeysDao.get()

    override suspend fun runTransaction(
        block: suspend () -> Unit,
    ) {
        remoteKeysDao.withTransaction(block)
    }

    override suspend fun updateRemoteKeys(
        nextPage: Int?,
        prevPage: Int?,
    ) {
        remoteKeysDao.upsert(
            RemoteKeysRoomModel(
                anchor = "characters",
                nextPage = nextPage,
                prevPage = prevPage,
            )
        )
    }
}
