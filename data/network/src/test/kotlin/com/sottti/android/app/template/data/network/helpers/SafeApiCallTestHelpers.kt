package com.sottti.android.app.template.data.network.helpers

import com.github.michaelbull.result.Result
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlinx.io.Buffer
import kotlinx.io.Source

internal fun createMockClient(
    handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData,
): HttpClient = HttpClient(MockEngine) {
    engine { addHandler { request -> handler(request) } }
    install(ContentNegotiation) { json() }
    expectSuccess = true
}

internal suspend fun HttpClient.fetchAsText(): Result<String, ExceptionApiModel> =
    safeApiCall { get(TEST_URL).bodyAsText() }

// Custom ByteReadChannel that throws IOException when content is awaited/read
val failingChannel = object : ByteReadChannel {
    override val closedCause: Throwable? = null
    override val isClosedForRead: Boolean = false

    @InternalAPI
    override val readBuffer: Source = Buffer()

    override suspend fun awaitContent(min: Int): Boolean {
        throw kotlinx.io.IOException("Simulated body read failure")
    }

    override fun cancel(cause: Throwable?) {}
}

internal const val ERROR_BODY_RESPONSE = """{"error":"test error"}"""
internal const val MALFORMED_JSON_RESPONSE = """{"message": "incomplete"""
internal const val SUCCESS_RESPONSE = "Success"
internal const val TEST_URL = "http://test.com/path"
