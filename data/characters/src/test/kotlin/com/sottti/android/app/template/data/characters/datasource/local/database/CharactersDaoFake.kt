package com.sottti.android.app.template.data.characters.datasource.local.database

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.characters.datasource.local.mapper.RoomPagingSourceFake
import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class CharactersDaoFake : CharactersDao {
    val saved = mutableListOf<CharacterRoomModel>()
    var clearCalled = false
    var upsertCalled = false

    private val charactersFlow = MutableStateFlow<List<CharacterRoomModel>>(emptyList())

    override fun observeCharacters(): PagingSource<Int, CharacterRoomModel> {
        return RoomPagingSourceFake { params ->
            val snapshot = saved.toList()
            val start = (params.key ?: 0).coerceAtLeast(0)
            val end = (start + params.loadSize).coerceAtMost(snapshot.size)

            PagingSource.LoadResult.Page(
                data = snapshot.subList(start, end),
                prevKey = if (start == 0) null else maxOf(0, start - params.loadSize),
                nextKey = if (end < snapshot.size) end else null,
                itemsBefore = start,
                itemsAfter = snapshot.size - end,
            )
        }
    }

    override suspend fun upsert(characters: List<CharacterRoomModel>) {
        upsertCalled = true
        characters.forEach { newCharacter ->
            val index = saved.indexOfFirst { it.id == newCharacter.id }
            when {
                index != -1 -> saved[index] = newCharacter
                else -> saved.add(newCharacter)
            }
        }
        charactersFlow.value = saved.toList()
    }

    override suspend fun upsert(character: CharacterRoomModel) {
        upsertCalled = true
        val index = saved.indexOfFirst { it.id == character.id }
        if (index != -1) saved[index] = character else saved.add(character)
        charactersFlow.value = saved.toList()
    }

    override suspend fun getCharacter(characterId: Int): CharacterRoomModel? {
        return saved.firstOrNull { it.id == characterId }
    }

    override fun observeCharacter(characterId: Int): Flow<CharacterRoomModel?> {
        return charactersFlow
            .map { list -> list.firstOrNull { it.id == characterId } }
            .distinctUntilChanged()
    }

    override suspend fun clear() {
        clearCalled = true
        saved.clear()
        charactersFlow.value = emptyList()
    }
}
