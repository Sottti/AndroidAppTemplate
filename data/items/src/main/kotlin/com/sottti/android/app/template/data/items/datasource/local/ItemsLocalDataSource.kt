package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemId
import kotlinx.coroutines.flow.Flow

internal interface ItemsLocalDataSource {
    fun observeItem(itemId: ItemId): Flow<Item?>
    fun observeItems(): PagingSource<Int, Item>
    suspend fun isExpired(itemId: ItemId): Boolean
    suspend fun clearAll()
    suspend fun clearAndInsert(items: List<Item>)
    suspend fun getNextRemotePage(): RemoteKeysRoomModel?
    suspend fun runTransaction(block: suspend () -> Unit)
    suspend fun updateRemoteKeys(nextPage: Int?, prevPage: Int?)
    suspend fun upsert(item: Item)
    suspend fun upsert(items: List<Item>)
}
