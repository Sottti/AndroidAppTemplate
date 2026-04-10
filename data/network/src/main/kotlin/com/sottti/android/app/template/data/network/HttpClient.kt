package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom

internal fun createHttpClientEngine(): HttpClientEngine = CIO.create()

internal fun createCoilHttpClient(
    engine: HttpClientEngine,
) = HttpClient(engine) { installLogging() }

internal fun createApiHttpClient(
    engine: HttpClientEngine,
) = HttpClient(engine) {
    installJson()

    defaultRequest {
        url.takeFrom(API_BASE_URL)
        contentType(ContentType.Application.Json)
    }

    installLogging()
}
