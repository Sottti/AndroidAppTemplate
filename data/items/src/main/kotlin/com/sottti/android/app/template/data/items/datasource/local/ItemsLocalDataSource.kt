package com.sottti.android.app.template.data.items.datasource.local

import androidx.paging.PagingSource
import com.sottti.android.app.template.model.Item

internal interface ItemsLocalDataSource {
    fun observeItems(): PagingSource<Int, Item>
    suspend fun saveItems(items: List<Item>)
    fun clearAll()
}
