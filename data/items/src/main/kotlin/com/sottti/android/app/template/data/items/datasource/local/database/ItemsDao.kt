package com.sottti.android.app.template.data.items.datasource.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(items: List<ItemRoomModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ItemRoomModel)

    @Transaction
    suspend fun cleanAndInsert(items: List<ItemRoomModel>) {
        clear()
        upsert(items)
    }

    @Query("SELECT * FROM items WHERE id = :itemId")
    suspend fun getItem(itemId: Int): ItemRoomModel

    @Query("SELECT * FROM items WHERE id = :itemId")
    fun observeItem(itemId: Int): Flow<ItemRoomModel>

    @Query("SELECT * FROM items ORDER BY id ASC")
    fun observeItems(): PagingSource<Int, ItemRoomModel>

    @Query("DELETE FROM items")
    suspend fun clear()
}
