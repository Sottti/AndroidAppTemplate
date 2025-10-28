package com.sottti.android.app.template.data.network.di

import android.content.Context
import com.sottti.android.app.template.data.network.MockHttpClientRouter
import com.sottti.android.app.template.data.network.createMockHttpClient
import com.sottti.android.app.template.data.network.data.API_PATH_ITEMS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
    ): HttpClient = createMockHttpClient(
        router = provideRouter { path ->
            context.assets.open(path).bufferedReader().use { it.readText() }
        })

    private fun provideRouter(
        load: (String) -> String,
    ): MockHttpClientRouter = { req ->
        when (req.url.encodedPath) {
            API_PATH_ITEMS -> HttpStatusCode.OK to load("db.json")
            else -> HttpStatusCode.NotFound to
                    """{"error":"route not mocked","path":"${req.url.encodedPath}"}"""
        }
    }
}
