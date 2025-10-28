package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom

private const val API_BASE_URL = "TODO()"

internal fun createHttpClient() = HttpClient(CIO) {
    installJson()

    defaultRequest {
        url.takeFrom(API_BASE_URL)
        contentType(ContentType.Application.Json)
    }

    installLogging()
}
