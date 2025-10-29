package com.sottti.android.app.template.data.items.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
internal data class RemoteKeysRoomModel(
    @PrimaryKey val anchor: String = "items",
    val nextPage: Int?,
    val prevPage: Int?,
)
