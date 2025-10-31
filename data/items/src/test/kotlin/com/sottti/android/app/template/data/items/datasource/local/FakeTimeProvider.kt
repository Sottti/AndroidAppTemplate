package com.sottti.android.app.template.data.items.datasource.local

import java.time.Instant

internal class FakeTimeProvider(
    initialTime: Long = Instant.EPOCH.toEpochMilli(),
) : TimeProvider {

    private var currentTime: Long = initialTime

    override fun now(): Long = currentTime

    fun set(timeInMillis: Long) {
        currentTime = timeInMillis
    }

    fun advanceByMillis(millis: Long) {
        currentTime += (millis)
    }
}
