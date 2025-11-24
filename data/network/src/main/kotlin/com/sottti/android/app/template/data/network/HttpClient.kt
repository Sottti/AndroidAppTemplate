package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom

internal const val API_BASE_URL = "https://punkapi.online/v3/"

internal fun createBaseHttpClient() = HttpClient(CIO) {
    installJson()
    installLogging()
}

internal fun createApiHttpClient() =
    createBaseHttpClient().config {
        defaultRequest {
            url.takeFrom(API_BASE_URL)
            contentType(ContentType.Application.Json)
        }
    }
