package com.sottti.android.app.template.data.characters.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.characters.datasource.local.mapper.CharacterMappingPagingSourceFake
import com.sottti.android.app.template.data.characters.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class CharactersLocalDataSourceFake : CharactersLocalDataSource {

    val saved: MutableList<Character> = mutableListOf()
    var remoteKeys: RemoteKeysRoomModel? = null

    var clearAllCalled = false
        private set
    var clearAndInsertCalled = false
        private set
    var upsertCalled = false
        private set

    private val charactersFlow = MutableStateFlow<List<Character>>(emptyList())
    var expiredIds: MutableSet<CharacterId> = mutableSetOf()

    override fun observeCharacter(characterId: CharacterId): Flow<Character?> =
        charactersFlow
            .map { list -> list.firstOrNull { it.id == characterId } }
            .distinctUntilChanged()

    override fun observeCharacters(): PagingSource<Int, Character> =
        CharacterMappingPagingSourceFake(saved)

    override suspend fun isExpired(characterId: CharacterId): Boolean = characterId in expiredIds

    override suspend fun clearAll() {
        clearAllCalled = true
        saved.clear()
        remoteKeys = null
        charactersFlow.value = saved.toList()
    }

    override suspend fun clearAndInsert(characters: List<Character>) {
        clearAndInsertCalled = true
        saved.clear()
        saved.addAll(characters)
        charactersFlow.value = saved.toList()
    }

    override suspend fun getNextRemotePage(): RemoteKeysRoomModel? {
        return remoteKeys
    }

    override suspend fun runTransaction(block: suspend () -> Unit) {
        block()
    }

    override suspend fun updateRemoteKeys(nextPage: Int?, prevPage: Int?) {
        remoteKeys = RemoteKeysRoomModel(
            anchor = "characters",
            nextPage = nextPage,
            prevPage = prevPage,
        )
    }

    override suspend fun upsert(character: Character) {
        upsertCalled = true
        val index = saved.indexOfFirst { it.id == character.id }
        if (index != -1) {
            saved[index] = character
        } else {
            saved.add(character)
        }
        charactersFlow.value = saved.toList()
    }

    override suspend fun upsert(characters: List<Character>) {
        upsertCalled = true
        characters.forEach { newCharacter ->
            val index = saved.indexOfFirst { it.id == newCharacter.id }
            if (index != -1) {
                saved[index] = newCharacter
            } else {
                saved.add(newCharacter)
            }
        }
        charactersFlow.value = saved.toList()
    }
}
