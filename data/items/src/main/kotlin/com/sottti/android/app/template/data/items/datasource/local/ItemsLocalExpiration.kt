package com.sottti.android.app.template.data.items.datasource.local

import com.sottti.android.app.template.data.items.datasource.local.model.ItemRoomModel
import kotlin.time.Duration.Companion.minutes

private val expirationTime = 30.minutes.inWholeMilliseconds

internal fun ItemRoomModel.isExpired(nowInMillis: Long) =
    nowInMillis - storedAt >= expirationTime
