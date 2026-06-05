package com.sottti.android.app.template.data.characters.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.sottti.android.app.template.data.characters.datasource.remote.api.CharactersApiCalls
import com.sottti.android.app.template.data.characters.datasource.remote.mapper.toDomain
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import javax.inject.Inject

internal class CharactersApiDataSource @Inject constructor(
    private val api: CharactersApiCalls,
) : CharactersRemoteDataSource {
    override suspend fun getCharacter(characterId: CharacterId): Result<Character> =
        api
            .getCharacter(characterId)
            .mapBoth(
                success = { characterApiModel -> Ok(characterApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )

    override suspend fun getCharacters(
        pageNumber: PageNumberApiModel,
    ): Result<List<Character>> =
        api
            .getCharacters(pageNumber = pageNumber)
            .mapBoth(
                success = { charactersApiModel -> Ok(charactersApiModel.toDomain()) },
                failure = { exception -> Err(exception) }
            )
}
