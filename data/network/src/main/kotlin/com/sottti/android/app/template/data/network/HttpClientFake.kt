package com.sottti.android.app.template.data.network

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

public fun createMockHttpClient(context: Context): HttpClient {
    val json = Json { ignoreUnknownKeys = true }
    val itemsJson = context.loadJsonAsset("items.json")
    val itemsById = buildItemsById(itemsJson, json)
    val engine = mockEngineForJson(API_PATH_BEERS, itemsJson, itemsById)

    return HttpClient(engine) {
        installJson()
        defaultRequest { contentType(ContentType.Application.Json) }
        installLogging()
    }
}

private fun Context.loadJsonAsset(fileName: String): String =
    assets.open(fileName).bufferedReader().use { it.readText() }

/**
 * Builds a map of items keyed by ID, encoding each item back to JSON.
 */
private fun buildItemsById(itemsJson: String, json: Json): Map<String, String> =
    json.parseToJsonElement(itemsJson)
        .jsonArray
        .associate { element ->
            val id = element.jsonObject["id"].toString().trim('"')
            id to element.toString()
        }

private fun mockEngineForJson(
    apiPath: String,
    allItemsJson: String,
    itemsById: Map<String, String>,
): MockEngine = MockEngine { request ->
    val path = request.url.encodedPath
    val headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())

    when {
        path == "/$apiPath" -> respond(content = allItemsJson, headers = headers)
        path.startsWith("/$apiPath/") -> {
            val id = path.removePrefix("/$apiPath/")
            itemsById[id]
                ?.let { respond(content = it, headers = headers) }
                ?: respondError(HttpStatusCode.NotFound)
        }

        else -> respondError(HttpStatusCode.NotFound)
    }
}
