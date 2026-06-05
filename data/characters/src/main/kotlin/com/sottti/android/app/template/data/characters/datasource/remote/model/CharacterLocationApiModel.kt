package com.sottti.android.app.template.data.characters.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterLocationApiModel(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
