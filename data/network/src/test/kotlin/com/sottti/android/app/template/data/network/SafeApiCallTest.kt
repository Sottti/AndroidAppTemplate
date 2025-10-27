package com.sottti.android.app.template.data.network

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.network.helpers.ERROR_BODY_RESPONSE
import com.sottti.android.app.template.data.network.helpers.MALFORMED_JSON_RESPONSE
import com.sottti.android.app.template.data.network.helpers.SUCCESS_RESPONSE
import com.sottti.android.app.template.data.network.helpers.TEST_URL
import com.sottti.android.app.template.data.network.helpers.createMockClient
import com.sottti.android.app.template.data.network.helpers.failingChannel
import com.sottti.android.app.template.data.network.helpers.fetchAsText
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import com.sottti.android.app.template.data.network.model.TestData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.UnknownHostException

internal class SafeApiCallTest {

    @Test
    fun `returns ok when client call is successful`() = runTest {
        val client = createMockClient { respond(SUCCESS_RESPONSE, HttpStatusCode.OK) }
        val result = client.fetchAsText()

        assertThat(result.isOk).isTrue()
        assertThat(result.get()).isEqualTo(SUCCESS_RESPONSE)
    }

    @Test
    fun `returns no internet when engine throws unknown host exception`() = runTest {
        val client = createMockClient { throw UnknownHostException(NO_INTERNET_ERROR_MESSAGE) }
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.NoInternet::class.java)
        assertThat(error?.message).isEqualTo(NO_INTERNET_ERROR_MESSAGE)
    }

    @Test
    fun `returns no internet with default message when unknown host exception message is null`() =
        runTest {
            val client = createMockClient { throw UnknownHostException(null) }
            val result = client.fetchAsText()

            assertThat(result.isErr).isTrue()
            val error = result.getError()
            assertThat(error).isInstanceOf(ExceptionApiModel.NoInternet::class.java)
            assertThat(error?.message).isEqualTo(NO_INTERNET_ERROR_MESSAGE)
        }

    @Test
    fun `returns timeout when engine throws socket timeout exception`() = runTest {
        val client = createMockClient { throw SocketTimeoutException(TIMEOUT_ERROR_MESSAGE) }
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.Timeout::class.java)
        assertThat(error?.message).isEqualTo(TIMEOUT_ERROR_MESSAGE)
    }

    @Test
    fun `returns timeout with default message when socket timeout exception message is null`() =
        runTest {
            val client = createMockClient { throw SocketTimeoutException(null) }
            val result = client.fetchAsText()

            assertThat(result.isErr).isTrue()
            val error = result.getError()
            assertThat(error).isInstanceOf(ExceptionApiModel.Timeout::class.java)
            assertThat(error?.message).isEqualTo(TIMEOUT_ERROR_MESSAGE)
        }

    @Test
    fun `returns client error when server returns 4xx`() = runTest {
        val client = createMockClient {
            respondError(
                HttpStatusCode.NotFound,
                ERROR_BODY_RESPONSE,
                headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
            )
        }
        // Ktor's client throws ClientRequestException automatically on 4xx
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.ClientError::class.java)
        error as ExceptionApiModel.ClientError
        assertThat(error.code).isEqualTo(HttpStatusCode.NotFound.value)
        assertThat(error.errorBody).isEqualTo(ERROR_BODY_RESPONSE)
        assertThat(error.message).contains("404")
    }

    @Test
    fun `returns server error when server returns 5xx`() = runTest {
        val client = createMockClient {
            respondError(HttpStatusCode.InternalServerError, ERROR_BODY_RESPONSE)
        }
        // Ktor's client throws ServerResponseException automatically on 5xx
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.ServerError::class.java)
        error as ExceptionApiModel.ServerError
        assertThat(error.code).isEqualTo(HttpStatusCode.InternalServerError.value)
        assertThat(error.message).contains("500")
    }

    @Test
    fun `returns redirect error when server returns 3xx and redirects are off`() = runTest {
        // This test needs a client configured specifically *not* to follow redirects.
        // We bypass the standard createMockClient helper here.
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respondError(
                        HttpStatusCode.MovedPermanently,
                        headers = headersOf(HttpHeaders.Location, TEST_URL),
                    )
                }
            }
            install(ContentNegotiation) { json() }
            followRedirects = false // The crucial setting for this test
            expectSuccess = true // Ensure exception is expected
        }

        // Ktor throws RedirectResponseException on 3xx if followRedirects = false and expectSuccess = true
        // However, this seems unreliable with MockEngine. Test is removed.

        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.RedirectError::class.java)
        error as ExceptionApiModel.RedirectError
        assertThat(error.code).isEqualTo(HttpStatusCode.MovedPermanently.value)
        assertThat(error.message).contains("301")
    }

    @Test
    fun `returns unknown error when engine throws generic exception`() = runTest {
        val unexpectedErrorMessage = "Unexpected Error"
        val client = createMockClient { throw RuntimeException(unexpectedErrorMessage) }
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
        assertThat(error?.message).isEqualTo(unexpectedErrorMessage)
    }

    @Test
    fun `returns unknown error with default message when generic exception message is null`() =
        runTest {
            val client = createMockClient { throw RuntimeException() }
            val result = client.fetchAsText()

            assertThat(result.isErr).isTrue()
            val error = result.getError()
            assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
            assertThat(error?.message).isEqualTo(UNKNOWN_ERROR_MESSAGE)
        }


    @Test
    fun `returns unknown error when deserialization fails`() = runTest {
        val client = createMockClient {
            respond(
                content = MALFORMED_JSON_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                ),
            )
        }
        // The apiCall block must attempt deserialization to trigger the exception
        // safeApiCall no longer catches SerializationException specifically
        val result = safeApiCall { client.get(TEST_URL).body<TestData>() }

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
        assertThat(error?.message).contains("Unexpected JSON token")
    }

    @Test
    fun `returns unknown error when reading client error body fails`() = runTest {

        val client = createMockClient {
            // Respond with 400 and the channel that will throw on read attempt
            respond(
                content = failingChannel,
                status = HttpStatusCode.BadRequest
            )
        }

        // Ktor throws ClientRequestException (expectSuccess=true).
        // safeApiCall catches it.
        // Inside the catch, it calls exception.response.bodyAsText().
        // bodyAsText calls awaitContent(), which throws IOException.
        // Since there's no inner try-catch, the IOException propagates up.
        // The final catch (exception: Exception) catches the IOException.
        val result = client.fetchAsText()

        assertThat(result.isErr).isTrue()
        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
        assertThat(error?.message).isEqualTo("Simulated body read failure")
    }
}
