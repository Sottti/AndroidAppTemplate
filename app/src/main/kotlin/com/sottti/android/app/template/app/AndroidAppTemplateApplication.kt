package com.sottti.android.app.template.app

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import com.sottti.android.app.template.data.network.createImageLoader
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@HiltAndroidApp
internal class AndroidAppTemplateApplication : Application(), SingletonImageLoader.Factory {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    fun interface NetworkEntryPoint {
        fun getBaseHttpClient(): HttpClient
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        val entryPoint = EntryPointAccessors.fromApplication(
            context = context,
            entryPoint = NetworkEntryPoint::class.java
        )
        return createImageLoader(context, entryPoint.getBaseHttpClient())
    }
}
