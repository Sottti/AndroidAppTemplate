package com.sottti.android.app.template.data.network.di

import android.content.Context
import coil3.ImageLoader
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.sottti.android.app.template.data.network.createApiHttpClient
import com.sottti.android.app.template.data.network.createCoilHttpClient
import com.sottti.android.app.template.data.network.createHttpClientEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import javax.inject.Named
import javax.inject.Singleton

private const val COIL_CLIENT_NAMED_ANNOTATION_VALUE = "CoilClient"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkDataModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine = createHttpClientEngine()

    @Provides
    @Singleton
    fun provideApiHttpClient(
        engine: HttpClientEngine,
    ): HttpClient = createApiHttpClient(engine)

    @Provides
    @Singleton
    @Named(COIL_CLIENT_NAMED_ANNOTATION_VALUE)
    fun provideCoilHttpClient(
        engine: HttpClientEngine,
    ): HttpClient = createCoilHttpClient(engine)

    @Provides
    @Singleton
    fun provideCoilImageLoader(
        @ApplicationContext context: Context,
        @Named(COIL_CLIENT_NAMED_ANNOTATION_VALUE) coilHttpClient: HttpClient
    ): ImageLoader = ImageLoader.Builder(context)
        .components { add(KtorNetworkFetcherFactory(coilHttpClient)) }
        .build()
}
