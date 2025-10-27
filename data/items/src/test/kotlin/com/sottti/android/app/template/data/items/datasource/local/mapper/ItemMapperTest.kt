package com.sottti.android.app.template.data.items.datasource.local.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.data.item
import com.sottti.android.app.template.data.items.datasource.data.itemRoomModel
import org.junit.Test

internal class ItemMapperTest {

    @Test
    fun `maps item to domain`() {
        assertThat(itemRoomModel.toDomain()).isEqualTo(item)
    }

    @Test
    fun `maps item to room`() {
        assertThat(item.toRoom()).isEqualTo(itemRoomModel)
    }
}
