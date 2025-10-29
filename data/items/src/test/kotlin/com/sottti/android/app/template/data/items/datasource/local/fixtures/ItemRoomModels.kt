package com.sottti.android.app.template.data.items.datasource.local.fixtures

import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.fixtures.fixtureItem
import com.sottti.android.app.template.fixtures.fixtureItem2

internal val itemRoomModel = fixtureItem.toRoom(System.currentTimeMillis())
internal val item2RoomModel = fixtureItem2.toRoom(System.currentTimeMillis())
