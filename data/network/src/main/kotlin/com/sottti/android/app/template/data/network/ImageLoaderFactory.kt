package com.sottti.android.app.template.data.network

import android.content.Context
import coil3.ImageLoader
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient

public fun createImageLoader(
    context: Context,
    httpClient: HttpClient,
): ImageLoader = ImageLoader.Builder(context)
    .components {
        add(KtorNetworkFetcherFactory(httpClient))
    }
    .build()
