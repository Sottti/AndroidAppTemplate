package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel

@Dao
internal interface RemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE anchor = :anchor LIMIT 1")
    suspend fun get(anchor: String = "items"): RemoteKeysRoomModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(keys: RemoteKeysRoomModel)

    @Query("DELETE FROM remote_keys")
    suspend fun clear()

    @Transaction
    suspend fun withTransaction(block: suspend () -> Unit) = block()
}
