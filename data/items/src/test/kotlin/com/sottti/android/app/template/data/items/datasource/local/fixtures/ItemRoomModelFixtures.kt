package com.sottti.android.app.template.data.items.datasource.local.fixtures

import com.sottti.android.app.template.data.items.datasource.local.TimeProviderFake
import com.sottti.android.app.template.data.items.datasource.local.mapper.toRoom
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.fixtures.fixtureItem2

private val now = TimeProviderFake().now()
internal val fixtureItem1RoomModel = fixtureItem1.toRoom(now)
internal val fixtureItem2RoomModel = fixtureItem2.toRoom(now)
