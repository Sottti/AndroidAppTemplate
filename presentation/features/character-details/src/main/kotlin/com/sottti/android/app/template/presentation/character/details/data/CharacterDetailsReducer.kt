package com.sottti.android.app.template.presentation.character.details.data

import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterImage
import com.sottti.android.app.template.domain.characters.model.CharacterName
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.character.details.R
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsRow
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState
import com.sottti.android.app.template.presentation.character.details.model.CharacterDetailsState.Loaded
import com.sottti.android.app.template.presentation.character.details.model.CharacterIdentityState
import com.sottti.android.app.template.presentation.character.details.model.CharacterImageState
import com.sottti.android.app.template.presentation.character.details.model.CharacterState

internal fun CharacterDetailsState.reduce(
    update: Character,
): CharacterDetailsState =
    Loaded(
        topBarState = topBarState.copy(title = update.name.value),
        character = update.toCharacterState(),
    )

private fun Character.toCharacterState(): CharacterState =
    CharacterState(
        image = image.toState(),
        identity = CharacterIdentityState(
            header = R.string.identity_header,
            name = name.toIdentityRow(),
        )
    )

private fun CharacterImage?.toState(): CharacterImageState = when (this) {
    null -> CharacterImageState.PlaceholderImage(Images.AvatarPlaceholder.state)
    else -> CharacterImageState.NetworkImage(description = description, url = url)
}

private fun CharacterName.toIdentityRow() =
    CharacterDetailsRow(
        headline = R.string.identity_name,
        trailing = value,
    )
