package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel

@Database(
    entities = [ItemRoomModel::class, RemoteKeysRoomModel::class],
    version = 1,
    exportSchema = false,
)
internal abstract class ItemsDatabase : RoomDatabase() {
    companion object {
        const val ITEMS_DATABASE_NAME: String = "items.db"
        fun create(context: Context) =
            Room.databaseBuilder(
                context = context,
                klass = ItemsDatabase::class.java,
                name = ITEMS_DATABASE_NAME,
            )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
    }

    abstract val itemsDao: ItemsDao
    abstract val remoteKeysDao: RemoteKeysDao
}
