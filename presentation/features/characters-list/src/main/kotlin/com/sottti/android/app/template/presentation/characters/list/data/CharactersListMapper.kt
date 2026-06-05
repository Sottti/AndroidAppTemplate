package com.sottti.android.app.template.presentation.characters.list.data

import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState.NetworkImage
import com.sottti.android.app.template.presentation.characters.list.model.CharacterImageState.PlaceholderImage
import com.sottti.android.app.template.presentation.characters.list.model.CharacterState

internal fun List<Character>.toUi() = map { character -> character.toUi() }

internal fun Character.toUi() = CharacterState(
    id = id.value,
    name = name.value,
    image = image?.let {
        NetworkImage(description = it.description, url = it.url)
    } ?: PlaceholderImage(
        Images.AvatarPlaceholder.state,
    ),
)
