package com.sottti.android.app.template.data.characters.datasource.local.database

import android.content.Context
import androidx.room.Room

internal fun Context.createDb() = Room
    .inMemoryDatabaseBuilder(this, CharactersDatabase::class.java)
    .allowMainThreadQueries()
    .build()
