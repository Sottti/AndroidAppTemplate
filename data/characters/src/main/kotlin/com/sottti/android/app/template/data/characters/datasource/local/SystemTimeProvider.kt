package com.sottti.android.app.template.data.characters.datasource.local

import java.time.Clock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SystemTimeProvider @Inject constructor() : TimeProvider {
    override fun now(): Long = Clock.systemUTC().millis()
}
