package com.sottti.android.app.template.data.items.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
internal data class ItemRoomModel(
    @PrimaryKey val id: Int,
    val imageDescription: String,
    val imageUrl: String,
    val name: String,
    val storedAt: Long,
)
