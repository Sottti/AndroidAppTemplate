package com.sottti.android.app.template.data.network.di

import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import coil3.ImageLoader
import coil3.test.FakeImageLoaderEngine
import coil3.test.intercept
import com.sottti.android.app.template.data.network.createMockHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkDataModule::class]
)
internal object NetworkDataModuleFake {

    @Provides
    @Singleton
    fun provideApiHttpClient(
        @ApplicationContext context: Context,
    ): HttpClient = createMockHttpClient(context)

    @Provides
    @Singleton
    fun provideCoilImageLoader(
        @ApplicationContext context: Context,
    ): ImageLoader {
        val placeholder = Color.MAGENTA.toDrawable()

        val engine =
            FakeImageLoaderEngine.Builder()
                .intercept({ it is String }, placeholder)
                .build()

        return ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
    }
}
