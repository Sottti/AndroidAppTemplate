package com.sottti.android.app.template.data.network

import com.google.common.truth.Truth.assertThat
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Url
import io.ktor.http.appendPathSegments
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class HttpClientTest {

    @Test
    fun `api client resolves list requests under api path`() = runTest {
        var requestedUrl: Url? = null
        val client = createCapturingApiClient { requestedUrl = it }

        client.get(API_PATH_CHARACTERS) {
            parameter(API_QUERY_PARAM_PAGE_NUMBER, 1)
        }

        assertThat(requestedUrl.toString())
            .isEqualTo("https://rickandmortyapi.com/api/character?page=1")
    }

    @Test
    fun `api client resolves detail requests under api path`() = runTest {
        var requestedUrl: Url? = null
        val client = createCapturingApiClient { requestedUrl = it }

        client.get(API_PATH_CHARACTERS) {
            url { appendPathSegments("1") }
        }

        assertThat(requestedUrl.toString())
            .isEqualTo("https://rickandmortyapi.com/api/character/1")
    }

    private fun createCapturingApiClient(
        captureUrl: (Url) -> Unit,
    ) = createApiHttpClient(
        MockEngine { request ->
            captureUrl(request.url)
            respond(
                content = "{}",
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString(),
                ),
            )
        },
    )
}
