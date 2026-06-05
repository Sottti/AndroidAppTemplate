package com.sottti.android.app.template.data.characters.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
internal data class RemoteKeysRoomModel(
    @PrimaryKey val anchor: String = "characters",
    val nextPage: Int?,
    val prevPage: Int?,
)
