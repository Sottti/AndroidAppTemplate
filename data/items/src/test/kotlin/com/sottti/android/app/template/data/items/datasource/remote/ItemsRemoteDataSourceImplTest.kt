package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.remote.api.FakeItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.model.PageApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.paginatedItemsApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.NoInternet
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ItemsRemoteDataSourceImplTest {

    private lateinit var apiCalls: ItemsApiCalls
    private lateinit var dataSource: ItemsRemoteDataSourceImpl

    @Test
    fun `when api call is success, then returns success with mapped domain model`() = runTest {
        apiCalls = FakeItemsApiCalls { _, _ -> Ok(paginatedItemsApiModel) }
        dataSource = ItemsRemoteDataSourceImpl(apiCalls)

        val result = dataSource.getItems(
            page = PageApiModel(value = 1),
            pageSize = PageSizeApiModel(value = 10)
        )

        assertThat(result.isOk).isTrue()
        val domainModel = result.get()!!
        assertThat(domainModel.nextPage).isEqualTo(2)
        assertThat(domainModel.items).hasSize(2)
        assertThat(domainModel.items.first().name.value)
            .isEqualTo(paginatedItemsApiModel.items.first().name)
    }

    @Test
    fun `when api call is failure, then returns error with exception`() = runTest {
        val exceptionMessage = "Network Failure"
        val exception = NoInternet(exceptionMessage)
        apiCalls = FakeItemsApiCalls { _, _ -> Err(exception) }
        dataSource = ItemsRemoteDataSourceImpl(apiCalls)

        val result = dataSource.getItems(PageApiModel(1), PageSizeApiModel(10))

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(NoInternet::class.java)
        assertThat(error).hasMessageThat().isEqualTo(exceptionMessage)
    }
}
