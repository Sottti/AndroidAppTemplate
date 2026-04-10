package com.sottti.android.app.template.data.items.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
internal data class ItemRoomModel(
    @PrimaryKey val id: Int,
    val created: String?,
    val episodes: List<String>?,
    val gender: String?,
    val imageDescription: String?,
    val imageUrl: String?,
    val locationName: String?,
    val locationUrl: String?,
    val name: String,
    val originName: String?,
    val originUrl: String?,
    val species: String?,
    val status: String?,
    val storedAt: Long,
    val type: String?,
    val url: String?,
)
