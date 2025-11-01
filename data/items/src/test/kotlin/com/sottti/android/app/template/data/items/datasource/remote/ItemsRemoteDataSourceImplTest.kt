package com.sottti.android.app.template.data.items.datasource.remote

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCallsFake
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItem1ApiModel
import com.sottti.android.app.template.data.items.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.data.items.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageSizeApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ItemsRemoteDataSourceImplTest {

    private lateinit var apiCalls: ItemsApiCallsFake
    private lateinit var dataSource: ItemsRemoteDataSourceImpl

    @Before
    fun setUp() {
        apiCalls = ItemsApiCallsFake()
        dataSource = ItemsRemoteDataSourceImpl(api = apiCalls)
    }

    @Test
    fun `get items on success returns mapped domain items`() = runTest {
        apiCalls.setSuccessResponse(listOf(fixtureItem1ApiModel))

        val result = dataSource.getItems(
            pageNumber = PageNumberApiModel(1),
            pageSize = PageSizeApiModel(10)
        )

        result.onSuccess { items ->
            assertThat(items).isNotEmpty()
            assertThat(items.first()).isEqualTo(fixtureItem1ApiModel.toDomain())
        }

        result.onFailure {
            throw AssertionError("Expected success but got failure")
        }

        assertThat(apiCalls.lastCalledPageNumber?.value).isEqualTo(1)
        assertThat(apiCalls.lastCalledPageSize?.value).isEqualTo(10)
    }

    @Test
    fun `get items on failure returns the exception`() = runTest {
        val exception = ExceptionApiModel.Unknown("Network Error")
        apiCalls.setErrorResponse(exception)

        val result = dataSource.getItems(
            pageNumber = PageNumberApiModel(1),
            pageSize = PageSizeApiModel(10)
        )

        result.onFailure { error ->
            assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
            assertThat(error.message).isEqualTo(exception.message)
        }
        result.onSuccess {
            throw AssertionError("Expected failure but got success")
        }
    }

    @Test
    fun `get items returns empty list when api returns empty`() = runTest {
        apiCalls.setSuccessResponse(emptyList())
        val result = dataSource.getItems(PageNumberApiModel(1), PageSizeApiModel(10))
        result
            .onSuccess { items -> assertThat(items).isEmpty() }
            .onFailure { throw AssertionError("Expected success but got failure") }
    }

    @Test
    fun `get items maps all entries preserving order`() = runTest {
        val apiItems = listOf(fixtureItem1ApiModel, fixtureItem1ApiModel.copy(id = 999))
        apiCalls.setSuccessResponse(apiItems)

        val result = dataSource.getItems(PageNumberApiModel(2), PageSizeApiModel(25))
        result.onSuccess { items ->
            assertThat(items).containsExactlyElementsIn(apiItems.map { it.toDomain() }).inOrder()
        }.onFailure { throw AssertionError("Expected success but got failure") }

        assertThat(apiCalls.lastCalledPageNumber?.value).isEqualTo(2)
        assertThat(apiCalls.lastCalledPageSize?.value).isEqualTo(25)
    }
}
