package com.sottti.android.app.template.data.characters.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sottti.android.app.template.data.characters.datasource.local.mapper.StringListConverter
import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
import com.sottti.android.app.template.data.characters.datasource.local.model.RemoteKeysRoomModel

@Database(
    entities = [CharacterRoomModel::class, RemoteKeysRoomModel::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(StringListConverter::class)
internal abstract class CharactersDatabase : RoomDatabase() {
    companion object {
        const val CHARACTERS_DATABASE_NAME: String = "characters.db"
        fun create(context: Context) =
            Room.databaseBuilder(
                context = context,
                klass = CharactersDatabase::class.java,
                name = CHARACTERS_DATABASE_NAME,
            )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
    }

    abstract val charactersDao: CharactersDao
    abstract val remoteKeysDao: RemoteKeysDao
}
