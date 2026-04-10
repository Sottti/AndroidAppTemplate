package com.sottti.android.app.template.domain.items.model

import com.sottti.android.app.template.domain.core.models.Url

public data class Item(
    val created: ItemCreated?,
    val episodes: List<ItemEpisodes>?,
    val gender: ItemGender?,
    val id: ItemId,
    val image: ItemImage?,
    val location: ItemLocation?,
    val name: ItemName,
    val origin: ItemOrigin?,
    val species: ItemSpecies?,
    val status: ItemStatus?,
    val type: ItemType?,
    val url: Url?,
)
