package com.sottti.android.app.template.data.items.datasource.local.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_ID_3
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_3
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_MIDDLE
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_ONLY_ONE
import com.sottti.android.app.template.data.items.datasource.data.ITEM_ITEM_NAME_ZULU
import com.sottti.android.app.template.data.items.datasource.data.REPLACED_NAME_1
import com.sottti.android.app.template.data.items.datasource.data.REPLACED_NAME_2
import com.sottti.android.app.template.data.items.datasource.data.item2RoomModel
import com.sottti.android.app.template.data.items.datasource.data.itemRoomModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
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
        dao = db.itemsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }
}
