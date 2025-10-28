package com.sottti.android.app.template.data.items.datasource.local

import javax.inject.Inject

internal class SystemTimeProvider @Inject constructor() : TimeProvider {
    override fun nowInMillis(): Long = System.currentTimeMillis()
}
