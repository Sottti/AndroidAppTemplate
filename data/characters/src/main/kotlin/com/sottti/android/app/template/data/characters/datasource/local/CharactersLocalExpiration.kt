package com.sottti.android.app.template.data.characters.datasource.local

import com.sottti.android.app.template.data.characters.datasource.local.model.CharacterRoomModel
import kotlin.time.Duration.Companion.minutes

private val expirationTime = 30.minutes.inWholeMilliseconds

internal fun CharacterRoomModel.isExpired(nowInMillis: Long) =
    nowInMillis - storedAt >= expirationTime
