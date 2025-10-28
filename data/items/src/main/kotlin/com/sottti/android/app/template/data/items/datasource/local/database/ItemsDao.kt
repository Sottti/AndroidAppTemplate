package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

@Dao
internal interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(items: List<ItemRoomModel>)

    @Query(""" SELECT * FROM items ORDER BY id ASC """)
    fun observeItems(): PagingSource<Int, ItemRoomModel>

    @Query("SELECT COUNT(*) FROM items")
    suspend fun count(): Int

    @Query("DELETE FROM items")
    suspend fun clearAll()

    @Query("SELECT storedAt FROM items ORDER BY storedAt ASC LIMIT 1")
    suspend fun getOldestItemTimestamp(): Long?
}
