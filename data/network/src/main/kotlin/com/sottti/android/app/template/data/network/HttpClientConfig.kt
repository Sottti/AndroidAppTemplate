package com.sottti.android.app.template.data.network

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun HttpClientConfig<*>.installJson(json: Json = provideJson()) {
    install(ContentNegotiation) {
        json(json)
        // Also parse JSON if a server responds with text/plain
        register(ContentType.Text.Plain, KotlinxSerializationConverter(json))
    }
}

private fun provideJson(): Json = Json {
    coerceInputValues = true
    explicitNulls = false
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

internal fun HttpClientConfig<*>.installLogging() {
    install(Logging) {
        level = LogLevel.ALL
        logger = io.ktor.client.plugins.logging.Logger.ANDROID
    }
}
