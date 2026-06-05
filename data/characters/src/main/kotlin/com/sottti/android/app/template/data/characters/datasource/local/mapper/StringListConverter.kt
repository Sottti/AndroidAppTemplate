package com.sottti.android.app.template.data.characters.datasource.local.mapper

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

internal class StringListConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? =
        value?.let { json.encodeToString(it) }

    @TypeConverter
    fun toStringList(value: String?): List<String>? =
        value?.let { json.decodeFromString(it) }
}
