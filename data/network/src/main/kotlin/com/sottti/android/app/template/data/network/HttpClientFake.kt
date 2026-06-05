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
import kotlinx.serialization.json.jsonPrimitive

public fun createMockHttpClient(context: Context): HttpClient =
    HttpClient(createMockEngineForJson(context)) {
        installJson()
        defaultRequest { contentType(ContentType.Application.Json) }
        installLogging()
    }

private fun createMockEngineForJson(context: Context): MockEngine {
    val itemsJson = context.loadJsonAsset("api_response_fake.json")
    val itemsById = buildItemsById(
        itemsJson = itemsJson,
        json = Json { ignoreUnknownKeys = true },
    )
    return MockEngine { request ->
        val path = request.url.encodedPath
        val headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())

        when {
            path == "/$API_PATH_CHARACTERS" -> respond(content = itemsJson, headers = headers)
            path.startsWith("/$API_PATH_CHARACTERS/") -> {
                val id = path.removePrefix("/$API_PATH_CHARACTERS/")
                itemsById[id]
                    ?.let { respond(content = it, headers = headers) }
                    ?: respondError(HttpStatusCode.NotFound)
            }

            else -> respondError(HttpStatusCode.NotFound)
        }
    }
}

private fun Context.loadJsonAsset(fileName: String): String =
    assets.open(fileName).bufferedReader().use { it.readText() }

private fun buildItemsById(itemsJson: String, json: Json): Map<String, String> =
    json
        .parseToJsonElement(itemsJson)
        .jsonObject[API_JSON_KEY_RESULTS]!!
        .jsonArray
        .associate { element ->
            val id = element.jsonObject[API_JSON_KEY_ID]?.jsonPrimitive?.content
                ?: throw IllegalArgumentException("JSON item is missing $API_JSON_KEY_ID field")
            id to element.toString()
        }
