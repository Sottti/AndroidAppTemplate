package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

internal typealias MockHttpClientRouter = (HttpRequestData) -> Pair<HttpStatusCode, String>

internal fun createMockHttpClient(
    router: MockHttpClientRouter,
): HttpClient = HttpClient(MockEngine) {
    engine {
        addHandler { request ->
            try {
                val (status, body) = router(request)
                respond(
                    content = body,
                    status = status,
                    headers = headersOf(
                        name = HttpHeaders.ContentType,
                        value = ContentType.Application.Json.toString()
                    ),
                )
            } catch (_: Throwable) {
                respondError(HttpStatusCode.InternalServerError)
            }
        }
    }

    installJson()

    installLogging()
}
