package com.sottti.android.app.template.data.characters.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharactersApiModel(
    @SerialName("info")
    val info: PageInfoApiModel,
    @SerialName("results")
    val results: List<CharacterApiModel>
)
