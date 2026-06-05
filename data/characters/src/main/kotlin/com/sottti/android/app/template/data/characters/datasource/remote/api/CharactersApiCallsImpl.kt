package com.sottti.android.app.template.data.characters.datasource.remote.api

import com.sottti.android.app.template.data.characters.datasource.remote.model.CharacterApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.CharactersApiModel
import com.sottti.android.app.template.data.characters.datasource.remote.model.PageNumberApiModel
import com.sottti.android.app.template.data.network.API_PATH_CHARACTERS
import com.sottti.android.app.template.data.network.API_QUERY_PARAM_PAGE_NUMBER
import com.sottti.android.app.template.data.network.model.ResultApiModel
import com.sottti.android.app.template.data.network.safeApiCall
import com.sottti.android.app.template.domain.characters.model.CharacterId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import javax.inject.Inject

internal class CharactersApiCallsImpl @Inject constructor(
    private val httpClient: HttpClient,
) : CharactersApiCalls {

    override suspend fun getCharacter(characterId: CharacterId): ResultApiModel<CharacterApiModel> =
        safeApiCall {
            httpClient.get(API_PATH_CHARACTERS) {
                url { appendPathSegments(characterId.value.toString()) }
            }.body()
        }

    override suspend fun getCharacters(
        pageNumber: PageNumberApiModel,
    ): ResultApiModel<CharactersApiModel> =
        safeApiCall {
            httpClient.get(API_PATH_CHARACTERS) {
                parameter(API_QUERY_PARAM_PAGE_NUMBER, pageNumber.value)
            }.body()
        }
}
