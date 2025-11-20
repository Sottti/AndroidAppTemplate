package com.sottti.android.app.template.data.network.di

import android.content.Context
import com.sottti.android.app.template.data.network.createMockHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkDataModule::class])
internal object NetworkDataModuleFake {

    @Provides
    @Singleton
    fun provideFakeHttpClient(
        @ApplicationContext context: Context,
    ): HttpClient = createMockHttpClient(context)
}
