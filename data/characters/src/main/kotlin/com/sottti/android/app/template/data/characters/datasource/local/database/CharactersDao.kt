package com.sottti.android.app.template.data.characters.datasource.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(characters: List<CharacterRoomModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: CharacterRoomModel)

    @Transaction
    suspend fun cleanAndInsert(characters: List<CharacterRoomModel>) {
        clear()
        upsert(characters)
    }

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacter(characterId: Int): CharacterRoomModel?

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun observeCharacter(characterId: Int): Flow<CharacterRoomModel?>

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun observeCharacters(): PagingSource<Int, CharacterRoomModel>

    @Query("DELETE FROM characters")
    suspend fun clear()
}
