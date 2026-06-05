@file:OptIn(ExperimentalTime::class)

package com.sottti.android.app.template.domain.characters.fixtures

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.domain.core.models.Url
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterCreated
import com.sottti.android.app.template.domain.characters.model.CharacterEpisodes
import com.sottti.android.app.template.domain.characters.model.CharacterGender
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.domain.characters.model.CharacterImage
import com.sottti.android.app.template.domain.characters.model.CharacterLocation
import com.sottti.android.app.template.domain.characters.model.CharacterName
import com.sottti.android.app.template.domain.characters.model.CharacterOrigin
import com.sottti.android.app.template.domain.characters.model.CharacterSpecies
import com.sottti.android.app.template.domain.characters.model.CharacterStatus
import com.sottti.android.app.template.domain.characters.model.CharacterType
import com.sottti.android.app.template.domain.characters.model.LocationName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

public val fixtureCharacter1: Character = Character(
    id = CharacterId(value = 1),
    name = CharacterName("Rick Sanchez"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Rick Sanchez"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    ),
    episodes = listOf(),
    url = Url("https://rickandmortyapi.com/api/character/1"),
    created = CharacterCreated(Instant.parse("2017-11-04T18:48:46.250Z"))
)

public val fixtureCharacter1MaxedNulls: Character = Character(
    id = CharacterId(value = 1),
    name = CharacterName("Morty Smith"),
    status = null,
    species = null,
    type = null,
    gender = null,
    origin = null,
    location = null,
    image = null,
    episodes = null,
    url = null,
    created = null,
)

public val fixtureCharacter2: Character = Character(
    id = CharacterId(value = 2),
    name = CharacterName("Morty Smith"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Morty Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/2.jpeg")
    ),
    episodes = listOf(
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/1")),
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/2"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/2"),
    created = CharacterCreated(Instant.parse("2017-11-04T18:50:21.651Z"))
)

public val fixtureCharacter3: Character = Character(
    id = CharacterId(value = 3),
    name = CharacterName("Summer Smith"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Female"),
    origin = CharacterOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Summer Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/3.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/3"),
    created = CharacterCreated(Instant.parse("2017-11-04T19:09:56.428Z"))
)

public val fixtureCharacter4: Character = Character(
    id = CharacterId(value = 4),
    name = CharacterName("Beth Smith"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Female"),
    origin = CharacterOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Beth Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/4.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/4"),
    created = CharacterCreated(Instant.parse("2017-11-04T19:22:43.665Z"))
)

public val fixtureCharacter5: Character = Character(
    id = CharacterId(value = 5),
    name = CharacterName("Jerry Smith"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Jerry Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/5.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/5"),
    created = CharacterCreated(Instant.parse("2017-11-04T19:26:56.301Z"))
)

public val fixtureCharacter6: Character = Character(
    id = CharacterId(value = 6),
    name = CharacterName("Abadango Cluster Princess"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Alien"),
    type = CharacterType(""),
    gender = CharacterGender("Female"),
    origin = CharacterOrigin(
        name = LocationName("Abadango"),
        url = Url("https://rickandmortyapi.com/api/location/2")
    ),
    location = CharacterLocation(
        name = LocationName("Abadango"),
        url = Url("https://rickandmortyapi.com/api/location/2")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Abadango Cluster Princess"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/6.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/27"))),
    url = Url("https://rickandmortyapi.com/api/character/6"),
    created = CharacterCreated(Instant.parse("2017-11-04T19:50:28.250Z"))
)

public val fixtureCharacter7: Character = Character(
    id = CharacterId(value = 7),
    name = CharacterName("Abradolf Lincler"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Human"),
    type = CharacterType("Genetic experiment"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = CharacterLocation(
        name = LocationName("Testicle Monster Dimension"),
        url = Url("https://rickandmortyapi.com/api/location/21")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Abradolf Lincler"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/7.jpeg")
    ),
    episodes = listOf(
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/10")),
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/11"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/7"),
    created = CharacterCreated(Instant.parse("2017-11-04T19:59:20.523Z"))
)

public val fixtureCharacter8: Character = Character(
    id = CharacterId(value = 8),
    name = CharacterName("Adjudicator Rick"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Adjudicator Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/8.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/28"))),
    url = Url("https://rickandmortyapi.com/api/character/8"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:03:34.737Z"))
)

public val fixtureCharacter9: Character = Character(
    id = CharacterId(value = 9),
    name = CharacterName("Agency Director"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Agency Director"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/9.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/24"))),
    url = Url("https://rickandmortyapi.com/api/character/9"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:06:54.976Z"))
)

public val fixtureCharacter10: Character = Character(
    id = CharacterId(value = 10),
    name = CharacterName("Alan Rails"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Human"),
    type = CharacterType("Superhuman (Ghost trains summoner)"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Worldender's lair"),
        url = Url("https://rickandmortyapi.com/api/location/4")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Alan Rails"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/10.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/25"))),
    url = Url("https://rickandmortyapi.com/api/character/10"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:19:09.017Z"))
)

public val fixtureCharacter11: Character = Character(
    id = CharacterId(value = 11),
    name = CharacterName("Albert Einstein"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Albert Einstein"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/11.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/12"))),
    url = Url("https://rickandmortyapi.com/api/character/11"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:20:20.965Z"))
)

public val fixtureCharacter12: Character = Character(
    id = CharacterId(value = 12),
    name = CharacterName("Alexander"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = CharacterLocation(
        name = LocationName("Anatomy Park"),
        url = Url("https://rickandmortyapi.com/api/location/5")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Alexander"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/12.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/3"))),
    url = Url("https://rickandmortyapi.com/api/character/12"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:32:33.144Z"))
)

public val fixtureCharacter13: Character = Character(
    id = CharacterId(value = 13),
    name = CharacterName("Alien Googah"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Alien"),
    type = CharacterType(""),
    gender = CharacterGender("unknown"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Alien Googah"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/13.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/31"))),
    url = Url("https://rickandmortyapi.com/api/character/13"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:33:30.779Z"))
)

public val fixtureCharacter14: Character = Character(
    id = CharacterId(value = 14),
    name = CharacterName("Alien Morty"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Alien"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Alien Morty"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/14.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/14"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:51:31.373Z"))
)

public val fixtureCharacter15: Character = Character(
    id = CharacterId(value = 15),
    name = CharacterName("Alien Rick"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Alien"),
    type = CharacterType(""),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Alien Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/15.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/15"),
    created = CharacterCreated(Instant.parse("2017-11-04T20:56:13.215Z"))
)

public val fixtureCharacter16: Character = Character(
    id = CharacterId(value = 16),
    name = CharacterName("Amish Cyborg"),
    status = CharacterStatus("Dead"),
    species = CharacterSpecies("Alien"),
    type = CharacterType("Parasite"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Amish Cyborg"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/16.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/15"))),
    url = Url("https://rickandmortyapi.com/api/character/16"),
    created = CharacterCreated(Instant.parse("2017-11-04T21:12:45.235Z"))
)

public val fixtureCharacter17: Character = Character(
    id = CharacterId(value = 17),
    name = CharacterName("Annie"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType(""),
    gender = CharacterGender("Female"),
    origin = CharacterOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = CharacterLocation(
        name = LocationName("Anatomy Park"),
        url = Url("https://rickandmortyapi.com/api/location/5")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Annie"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/17.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/3"))),
    url = Url("https://rickandmortyapi.com/api/character/17"),
    created = CharacterCreated(Instant.parse("2017-11-04T22:21:24.481Z"))
)

public val fixtureCharacter18: Character = Character(
    id = CharacterId(value = 18),
    name = CharacterName("Antenna Morty"),
    status = CharacterStatus("Alive"),
    species = CharacterSpecies("Human"),
    type = CharacterType("Human with antennae"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Antenna Morty"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/18.jpeg")
    ),
    episodes = listOf(
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/10")),
        CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/28"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/18"),
    created = CharacterCreated(Instant.parse("2017-11-04T22:25:29.008Z"))
)

public val fixtureCharacter19: Character = Character(
    id = CharacterId(value = 19),
    name = CharacterName("Antenna Rick"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Human"),
    type = CharacterType("Human with antennae"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("unknown"),
        url = Url("")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Antenna Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/19.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/19"),
    created = CharacterCreated(Instant.parse("2017-11-04T22:28:13.756Z"))
)

public val fixtureCharacter20: Character = Character(
    id = CharacterId(value = 20),
    name = CharacterName("Ants in my Eyes Johnson"),
    status = CharacterStatus("unknown"),
    species = CharacterSpecies("Human"),
    type = CharacterType("Human with ants in his eyes"),
    gender = CharacterGender("Male"),
    origin = CharacterOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = CharacterLocation(
        name = LocationName("Interdimensional Cable"),
        url = Url("https://rickandmortyapi.com/api/location/6")
    ),
    image = CharacterImage(
        description = ImageContentDescription("Ants in my Eyes Johnson"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/20.jpeg")
    ),
    episodes = listOf(CharacterEpisodes(Url("https://rickandmortyapi.com/api/episode/8"))),
    url = Url("https://rickandmortyapi.com/api/character/20"),
    created = CharacterCreated(Instant.parse("2017-11-04T22:34:53.659Z"))
)

public val listOfTwoCharacters: List<Character> = listOf(fixtureCharacter1, fixtureCharacter2)
public val listOfMultipleCharacters: List<Character> = listOf(
    fixtureCharacter1,
    fixtureCharacter2,
    fixtureCharacter3,
    fixtureCharacter4,
    fixtureCharacter5,
    fixtureCharacter6,
    fixtureCharacter7,
    fixtureCharacter8,
    fixtureCharacter9,
    fixtureCharacter10,
    fixtureCharacter11,
    fixtureCharacter12,
    fixtureCharacter13,
    fixtureCharacter14,
    fixtureCharacter15,
    fixtureCharacter16,
    fixtureCharacter17,
    fixtureCharacter18,
    fixtureCharacter19,
    fixtureCharacter20,
)
