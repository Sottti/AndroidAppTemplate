package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ItemsDaoTest {
    private lateinit var db: ItemsDatabase
    private lateinit var dao: ItemsDao

    @Before
    fun setUp() {
        db = ApplicationProvider
            .getApplicationContext<Context>()
            .createDb()
        dao = db.itemsDao
    }

    @After
    fun tearDown() {
        db.close()
    }
}
