package com.sottti.android.app.template.domain.characters.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@JvmInline
@OptIn(ExperimentalTime::class)
public value class CharacterCreated(public val value: Instant)
