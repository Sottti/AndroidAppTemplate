package com.sottti.android.app.template.data.characters.datasource.remote

import com.github.michaelbull.result.onErr
import com.github.michaelbull.result.onOk
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.remote.api.CharactersApiCallsFake
import com.sottti.android.app.template.data.characters.datasource.remote.fixtures.fixtureCharacter1ApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class CharactersApiDataSourceTest {

    private lateinit var apiCalls: CharactersApiCallsFake
    private lateinit var dataSource: CharactersApiDataSource

    @Before
    fun setUp() {
        apiCalls = CharactersApiCallsFake()
        dataSource = CharactersApiDataSource(api = apiCalls)
    }

    @Test
    fun `get characters on success returns mapped domain characters`() = runTest {
        apiCalls.setSuccessResponse(listOf(fixtureCharacter1ApiModel))

        val result = dataSource.getCharacters(
            pageNumber = PageNumberApiModel(1),
        )

        result.onOk { characters ->
            assertThat(characters).isNotEmpty()
            assertThat(characters.first()).isEqualTo(fixtureCharacter1ApiModel.toDomain())
        }

        result.onErr {
            throw AssertionError("Expected success but got failure")
        }

        assertThat(apiCalls.lastCalledPageNumber?.value).isEqualTo(1)
    }

    @Test
    fun `get characters on failure returns the exception`() = runTest {
        val exception = ExceptionApiModel.Unknown("Network Error")
        apiCalls.setErrorResponse(exception)

        val result = dataSource.getCharacters(
            pageNumber = PageNumberApiModel(1),
        )

        result.onErr { error ->
            assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
            assertThat(error.message).isEqualTo(exception.message)
        }
        result.onOk {
            throw AssertionError("Expected failure but got success")
        }
    }

    @Test
    fun `get characters returns empty list when api returns empty`() = runTest {
        apiCalls.setSuccessResponse(emptyList())
        val result = dataSource.getCharacters(PageNumberApiModel(1))
        result
            .onOk { characters -> assertThat(characters).isEmpty() }
            .onErr { throw AssertionError("Expected success but got failure") }
    }

    @Test
    fun `get characters maps all entries preserving order`() = runTest {
        val apiCharacters = listOf(fixtureCharacter1ApiModel, fixtureCharacter1ApiModel.copy(id = 999))
        apiCalls.setSuccessResponse(apiCharacters)

        val result = dataSource.getCharacters(PageNumberApiModel(2))
        result.onOk { characters ->
            assertThat(characters).containsExactlyElementsIn(apiCharacters.map { it.toDomain() }).inOrder()
        }.onErr { throw AssertionError("Expected success but got failure") }

        assertThat(apiCalls.lastCalledPageNumber?.value).isEqualTo(2)
    }
}
