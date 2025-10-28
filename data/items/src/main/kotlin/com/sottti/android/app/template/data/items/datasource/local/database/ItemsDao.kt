package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel

@Dao
internal interface ItemsDao {

    @Transaction
    suspend fun clearAndInsertOrUpdate(
        clearExisting: Boolean,
        items: List<ItemRoomModel>,
    ) {
        if (clearExisting) clearAll()
        insertOrUpdate(items)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(items: List<ItemRoomModel>)

    @Query("DELETE FROM items")
    suspend fun clearAll()

    @Query(""" SELECT * FROM items ORDER BY name ASC """)
    fun observeItems(): PagingSource<Int, ItemRoomModel>
}
