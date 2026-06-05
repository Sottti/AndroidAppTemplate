package com.sottti.android.app.template.data.characters.datasource.local

internal fun interface TimeProvider {
    fun now(): Long
}
