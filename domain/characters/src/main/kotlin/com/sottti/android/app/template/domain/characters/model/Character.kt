package com.sottti.android.app.template.domain.characters.model

import com.sottti.android.app.template.domain.core.models.Url

public data class Character(
    val created: CharacterCreated?,
    val episodes: List<CharacterEpisodes>?,
    val gender: CharacterGender?,
    val id: CharacterId,
    val image: CharacterImage?,
    val location: CharacterLocation?,
    val name: CharacterName,
    val origin: CharacterOrigin?,
    val species: CharacterSpecies?,
    val status: CharacterStatus?,
    val type: CharacterType?,
    val url: Url?,
)
