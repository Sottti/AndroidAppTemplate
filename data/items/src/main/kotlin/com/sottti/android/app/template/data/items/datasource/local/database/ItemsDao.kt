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
    suspend fun insertOrUpdate(items: List<ItemRoomModel>)

    @Query(""" SELECT * FROM items ORDER BY name ASC """)
    fun observeItems(): PagingSource<Int, ItemRoomModel>

    @Query("DELETE FROM items")
    suspend fun clearAll()
}
