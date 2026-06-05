package com.sottti.android.app.template.data.characters.datasource.remote

import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId

internal interface CharactersRemoteDataSource {
    suspend fun getCharacter(characterId: CharacterId): Result<Character>
    suspend fun getCharacters(pageNumber: PageNumberApiModel): Result<List<Character>>

    companion object {
        const val PAGE_SIZE = 20
    }
}
