package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.model.paginatedItemsModel
import com.sottti.android.app.template.data.items.datasource.remote.model.paginatedItemsApiModel
import org.junit.Test

internal class ItemsRemoteDataMapperTest {

    @Test
    fun `paginated items api model correctly maps to domain`() {
        assertThat(paginatedItemsApiModel.toDomain())
            .isEqualTo(paginatedItemsModel)
    }
}
