package com.sottti.android.app.template.data.characters.datasource.local.fixtures

import com.sottti.android.app.template.data.characters.datasource.local.TimeProviderFake
import com.sottti.android.app.template.data.characters.datasource.local.mapper.toRoom
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter2

private val now = TimeProviderFake().now()
internal val fixtureCharacter1RoomModel = fixtureCharacter1.toRoom(now)
internal val fixtureCharacter2RoomModel = fixtureCharacter2.toRoom(now)
