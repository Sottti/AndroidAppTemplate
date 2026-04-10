package com.sottti.android.app.template.domain.items.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@JvmInline
@OptIn(ExperimentalTime::class)
public value class ItemCreated(public val value: Instant)
