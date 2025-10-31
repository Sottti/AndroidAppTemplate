package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.room.Room

internal fun Context.createDb() = Room
    .inMemoryDatabaseBuilder(this, ItemsDatabase::class.java)
    .allowMainThreadQueries()
    .build()
