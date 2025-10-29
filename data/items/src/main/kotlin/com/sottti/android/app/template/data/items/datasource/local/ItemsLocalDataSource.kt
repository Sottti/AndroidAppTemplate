package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel
import com.sottti.android.app.template.model.Item

internal interface ItemsLocalDataSource {
    fun observeItems(): PagingSource<Int, Item>
    suspend fun clearAll()
    suspend fun clearAndInsert(items: List<Item>)
    suspend fun getNextRemotePage(): RemoteKeysRoomModel?
    suspend fun runTransaction(block: suspend () -> Unit)
    suspend fun updateRemoteKeys(nextPage: Int?, prevPage: Int?)
    suspend fun upsert(items: List<Item>)
}
