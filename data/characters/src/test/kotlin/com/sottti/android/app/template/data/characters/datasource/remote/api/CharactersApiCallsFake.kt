package com.sottti.android.app.template.data.characters.datasource.remote.api

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.annotation.UnsafeResultErrorAccess
import com.github.michaelbull.result.annotation.UnsafeResultValueAccess
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharactersApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageInfoApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.domain.characters.model.CharacterId

internal class CharactersApiCallsFake : CharactersApiCalls {

    private var response: ResultApiModel<CharactersApiModel>? = null

    var lastCalledPageNumber: PageNumberApiModel? = null

    fun setSuccessResponse(characters: List<CharacterApiModel>) {
        response = Ok(
            CharactersApiModel(
                info = PageInfoApiModel(
                    count = characters.size,
                    pages = 1,
                    next = null,
                    prev = null,
                ),
                results = characters,
            )
        )
    }

    fun setErrorResponse(exception: Throwable) {
        response = Err(Unknown(exception.message ?: "Unknown"))
    }

    @OptIn(UnsafeResultValueAccess::class, UnsafeResultErrorAccess::class)
    override suspend fun getCharacter(characterId: CharacterId): ResultApiModel<CharacterApiModel> {
        val listResult = checkNotNull(response) { "Test response was not set in fake" }
        return when {
            listResult.isOk -> {
                val character = listResult.value.results.firstOrNull { it.id == characterId.value }
                    ?: return Err(Unknown("Character with id ${characterId.value} not found"))
                Ok(character)
            }

            listResult.isErr -> Err(listResult.error)
            else -> error("Test response was not set in fake")
        }
    }

    override suspend fun getCharacters(
        pageNumber: PageNumberApiModel,
    ): ResultApiModel<CharactersApiModel> {
        lastCalledPageNumber = pageNumber
        return response ?: error("Test response was not set in fake")
    }
}
