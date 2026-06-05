package com.sottti.android.app.template.data.characters.datasource.remote.api

import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharactersApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.domain.characters.model.CharacterId

internal interface CharactersApiCalls {
    suspend fun getCharacter(characterId: CharacterId): ResultApiModel<CharacterApiModel>
    suspend fun getCharacters(pageNumber: PageNumberApiModel): ResultApiModel<CharactersApiModel>
}
