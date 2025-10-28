package com.sottti.android.app.template.data.items.datasource.local

internal fun interface TimeProvider {
    fun nowInMillis(): Long
}
