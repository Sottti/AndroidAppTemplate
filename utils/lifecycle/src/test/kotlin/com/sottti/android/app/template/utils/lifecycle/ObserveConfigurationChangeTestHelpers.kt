package com.sottti.android.app.template.utils.lifecycle

import android.app.Application
import android.content.ComponentCallbacks
import android.content.ContextWrapper
import android.content.res.Configuration

internal class CapturingContext(base: Application) : ContextWrapper(base) {
    var callbacks: ComponentCallbacks? = null
        private set

    override fun registerComponentCallbacks(callback: ComponentCallbacks) {
        callbacks = callback
        super.registerComponentCallbacks(callback)
    }

    override fun unregisterComponentCallbacks(callback: ComponentCallbacks) {
        if (callbacks === callback) callbacks = null
        super.unregisterComponentCallbacks(callback)
    }
}

internal fun Application.setOrientation(orientation: Int): Configuration {
    val newConfig = Configuration(resources.configuration).apply {
        this.orientation = orientation
    }

    @Suppress("DEPRECATION")
    resources.updateConfiguration(newConfig, resources.displayMetrics)
    return newConfig
}

internal fun Application.currentOrientationLabel(): String =
    when (resources.configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> PORTRAIT
        Configuration.ORIENTATION_LANDSCAPE -> LANDSCAPE
        else -> UNDEFINED
    }

internal const val LANDSCAPE = "landscape"
internal const val PORTRAIT = "portrait"
internal const val UNDEFINED = "undefined"
