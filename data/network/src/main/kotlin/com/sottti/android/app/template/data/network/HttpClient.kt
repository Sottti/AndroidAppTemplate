package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val API_BASE_URL = "https://androidapptemplateapi.onrender.com"

internal fun createHttpClient() = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            Json {
                coerceInputValues = true
                explicitNulls = false
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            }
        )
    }

    defaultRequest {
        url.takeFrom(API_BASE_URL)
        contentType(ContentType.Application.Json)
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.ANDROID
    }
}
