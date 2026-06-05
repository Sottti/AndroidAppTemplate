package com.sottti.android.app.template.data.characters.datasource.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.annotation.UnsafeResultErrorAccess
import com.github.michaelbull.result.annotation.UnsafeResultValueAccess
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.domain.core.models.Result
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId

internal class CharactersRemoteDataSourceFake : CharactersRemoteDataSource {

    private var response: Result<List<Character>>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null
    var lastCalledCharacterId: CharacterId? = null

    fun setSuccessResponse(characters: List<Character>) {
        response = Ok(characters)
    }

    fun setErrorResponse(exception: Exception) {
        response = Err(exception)
    }

    @OptIn(UnsafeResultValueAccess::class, UnsafeResultErrorAccess::class)
    override suspend fun getCharacter(characterId: CharacterId): Result<Character> {
        lastCalledCharacterId = characterId
        val listResult = checkNotNull(response) { "Test response was not set in fake" }

        return when {
            listResult.isOk -> {
                val character = listResult.value.firstOrNull { it.id.value == characterId.value }
                    ?: return Err(Unknown("Character with id ${characterId.value} not found"))
                Ok(character)
            }

            listResult.isErr -> Err(listResult.error)
            else -> error("Unexpected result state")
        }
    }

    override suspend fun getCharacters(
        pageNumber: PageNumberApiModel,
    ): Result<List<Character>> {
        lastCalledPageNumber = pageNumber
        return checkNotNull(response) { "Test response was not set in the fake" }
    }
}
