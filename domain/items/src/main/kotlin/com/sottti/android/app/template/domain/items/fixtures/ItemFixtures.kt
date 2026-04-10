@file:OptIn(ExperimentalTime::class)

package com.sottti.android.app.template.domain.items.fixtures

import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.domain.core.models.Url
import com.sottti.android.app.template.domain.items.model.Item
import com.sottti.android.app.template.domain.items.model.ItemCreated
import com.sottti.android.app.template.domain.items.model.ItemEpisodes
import com.sottti.android.app.template.domain.items.model.ItemGender
import com.sottti.android.app.template.domain.items.model.ItemId
import com.sottti.android.app.template.domain.items.model.ItemImage
import com.sottti.android.app.template.domain.items.model.ItemLocation
import com.sottti.android.app.template.domain.items.model.ItemName
import com.sottti.android.app.template.domain.items.model.ItemOrigin
import com.sottti.android.app.template.domain.items.model.ItemSpecies
import com.sottti.android.app.template.domain.items.model.ItemStatus
import com.sottti.android.app.template.domain.items.model.ItemType
import com.sottti.android.app.template.domain.items.model.LocationName
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

public val fixtureItem1: Item = Item(
    id = ItemId(value = 1),
    name = ItemName("Rick Sanchez"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Rick Sanchez"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    ),
    episodes = listOf(),
    url = Url("https://rickandmortyapi.com/api/character/1"),
    created = ItemCreated(Instant.parse("2017-11-04T18:48:46.250Z"))
)

public val fixtureItem1MaxedNulls: Item = Item(
    id = ItemId(value = 1),
    name = ItemName("Morty Smith"),
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

public val fixtureItem2: Item = Item(
    id = ItemId(value = 2),
    name = ItemName("Morty Smith"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Morty Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/2.jpeg")
    ),
    episodes = listOf(
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/1")),
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/2"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/2"),
    created = ItemCreated(Instant.parse("2017-11-04T18:50:21.651Z"))
)

public val fixtureItem3: Item = Item(
    id = ItemId(value = 3),
    name = ItemName("Summer Smith"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Female"),
    origin = ItemOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Summer Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/3.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/3"),
    created = ItemCreated(Instant.parse("2017-11-04T19:09:56.428Z"))
)

public val fixtureItem4: Item = Item(
    id = ItemId(value = 4),
    name = ItemName("Beth Smith"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Female"),
    origin = ItemOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Beth Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/4.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/4"),
    created = ItemCreated(Instant.parse("2017-11-04T19:22:43.665Z"))
)

public val fixtureItem5: Item = Item(
    id = ItemId(value = 5),
    name = ItemName("Jerry Smith"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Jerry Smith"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/5.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/6"))),
    url = Url("https://rickandmortyapi.com/api/character/5"),
    created = ItemCreated(Instant.parse("2017-11-04T19:26:56.301Z"))
)

public val fixtureItem6: Item = Item(
    id = ItemId(value = 6),
    name = ItemName("Abadango Cluster Princess"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Alien"),
    type = ItemType(""),
    gender = ItemGender("Female"),
    origin = ItemOrigin(
        name = LocationName("Abadango"),
        url = Url("https://rickandmortyapi.com/api/location/2")
    ),
    location = ItemLocation(
        name = LocationName("Abadango"),
        url = Url("https://rickandmortyapi.com/api/location/2")
    ),
    image = ItemImage(
        description = ImageContentDescription("Abadango Cluster Princess"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/6.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/27"))),
    url = Url("https://rickandmortyapi.com/api/character/6"),
    created = ItemCreated(Instant.parse("2017-11-04T19:50:28.250Z"))
)

public val fixtureItem7: Item = Item(
    id = ItemId(value = 7),
    name = ItemName("Abradolf Lincler"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Human"),
    type = ItemType("Genetic experiment"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = ItemLocation(
        name = LocationName("Testicle Monster Dimension"),
        url = Url("https://rickandmortyapi.com/api/location/21")
    ),
    image = ItemImage(
        description = ImageContentDescription("Abradolf Lincler"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/7.jpeg")
    ),
    episodes = listOf(
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/10")),
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/11"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/7"),
    created = ItemCreated(Instant.parse("2017-11-04T19:59:20.523Z"))
)

public val fixtureItem8: Item = Item(
    id = ItemId(value = 8),
    name = ItemName("Adjudicator Rick"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Adjudicator Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/8.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/28"))),
    url = Url("https://rickandmortyapi.com/api/character/8"),
    created = ItemCreated(Instant.parse("2017-11-04T20:03:34.737Z"))
)

public val fixtureItem9: Item = Item(
    id = ItemId(value = 9),
    name = ItemName("Agency Director"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Agency Director"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/9.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/24"))),
    url = Url("https://rickandmortyapi.com/api/character/9"),
    created = ItemCreated(Instant.parse("2017-11-04T20:06:54.976Z"))
)

public val fixtureItem10: Item = Item(
    id = ItemId(value = 10),
    name = ItemName("Alan Rails"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Human"),
    type = ItemType("Superhuman (Ghost trains summoner)"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Worldender's lair"),
        url = Url("https://rickandmortyapi.com/api/location/4")
    ),
    image = ItemImage(
        description = ImageContentDescription("Alan Rails"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/10.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/25"))),
    url = Url("https://rickandmortyapi.com/api/character/10"),
    created = ItemCreated(Instant.parse("2017-11-04T20:19:09.017Z"))
)

public val fixtureItem11: Item = Item(
    id = ItemId(value = 11),
    name = ItemName("Albert Einstein"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Albert Einstein"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/11.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/12"))),
    url = Url("https://rickandmortyapi.com/api/character/11"),
    created = ItemCreated(Instant.parse("2017-11-04T20:20:20.965Z"))
)

public val fixtureItem12: Item = Item(
    id = ItemId(value = 12),
    name = ItemName("Alexander"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = ItemLocation(
        name = LocationName("Anatomy Park"),
        url = Url("https://rickandmortyapi.com/api/location/5")
    ),
    image = ItemImage(
        description = ImageContentDescription("Alexander"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/12.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/3"))),
    url = Url("https://rickandmortyapi.com/api/character/12"),
    created = ItemCreated(Instant.parse("2017-11-04T20:32:33.144Z"))
)

public val fixtureItem13: Item = Item(
    id = ItemId(value = 13),
    name = ItemName("Alien Googah"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Alien"),
    type = ItemType(""),
    gender = ItemGender("unknown"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Alien Googah"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/13.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/31"))),
    url = Url("https://rickandmortyapi.com/api/character/13"),
    created = ItemCreated(Instant.parse("2017-11-04T20:33:30.779Z"))
)

public val fixtureItem14: Item = Item(
    id = ItemId(value = 14),
    name = ItemName("Alien Morty"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Alien"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Alien Morty"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/14.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/14"),
    created = ItemCreated(Instant.parse("2017-11-04T20:51:31.373Z"))
)

public val fixtureItem15: Item = Item(
    id = ItemId(value = 15),
    name = ItemName("Alien Rick"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Alien"),
    type = ItemType(""),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Alien Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/15.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/15"),
    created = ItemCreated(Instant.parse("2017-11-04T20:56:13.215Z"))
)

public val fixtureItem16: Item = Item(
    id = ItemId(value = 16),
    name = ItemName("Amish Cyborg"),
    status = ItemStatus("Dead"),
    species = ItemSpecies("Alien"),
    type = ItemType("Parasite"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Earth (Replacement Dimension)"),
        url = Url("https://rickandmortyapi.com/api/location/20")
    ),
    image = ItemImage(
        description = ImageContentDescription("Amish Cyborg"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/16.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/15"))),
    url = Url("https://rickandmortyapi.com/api/character/16"),
    created = ItemCreated(Instant.parse("2017-11-04T21:12:45.235Z"))
)

public val fixtureItem17: Item = Item(
    id = ItemId(value = 17),
    name = ItemName("Annie"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType(""),
    gender = ItemGender("Female"),
    origin = ItemOrigin(
        name = LocationName("Earth (C-137)"),
        url = Url("https://rickandmortyapi.com/api/location/1")
    ),
    location = ItemLocation(
        name = LocationName("Anatomy Park"),
        url = Url("https://rickandmortyapi.com/api/location/5")
    ),
    image = ItemImage(
        description = ImageContentDescription("Annie"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/17.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/3"))),
    url = Url("https://rickandmortyapi.com/api/character/17"),
    created = ItemCreated(Instant.parse("2017-11-04T22:21:24.481Z"))
)

public val fixtureItem18: Item = Item(
    id = ItemId(value = 18),
    name = ItemName("Antenna Morty"),
    status = ItemStatus("Alive"),
    species = ItemSpecies("Human"),
    type = ItemType("Human with antennae"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Citadel of Ricks"),
        url = Url("https://rickandmortyapi.com/api/location/3")
    ),
    image = ItemImage(
        description = ImageContentDescription("Antenna Morty"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/18.jpeg")
    ),
    episodes = listOf(
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/10")),
        ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/28"))
    ),
    url = Url("https://rickandmortyapi.com/api/character/18"),
    created = ItemCreated(Instant.parse("2017-11-04T22:25:29.008Z"))
)

public val fixtureItem19: Item = Item(
    id = ItemId(value = 19),
    name = ItemName("Antenna Rick"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Human"),
    type = ItemType("Human with antennae"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("unknown"),
        url = Url("")
    ),
    image = ItemImage(
        description = ImageContentDescription("Antenna Rick"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/19.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/10"))),
    url = Url("https://rickandmortyapi.com/api/character/19"),
    created = ItemCreated(Instant.parse("2017-11-04T22:28:13.756Z"))
)

public val fixtureItem20: Item = Item(
    id = ItemId(value = 20),
    name = ItemName("Ants in my Eyes Johnson"),
    status = ItemStatus("unknown"),
    species = ItemSpecies("Human"),
    type = ItemType("Human with ants in his eyes"),
    gender = ItemGender("Male"),
    origin = ItemOrigin(
        name = LocationName("unknown"),
        url = Url("")
    ),
    location = ItemLocation(
        name = LocationName("Interdimensional Cable"),
        url = Url("https://rickandmortyapi.com/api/location/6")
    ),
    image = ItemImage(
        description = ImageContentDescription("Ants in my Eyes Johnson"),
        url = ImageUrl("https://rickandmortyapi.com/api/character/avatar/20.jpeg")
    ),
    episodes = listOf(ItemEpisodes(Url("https://rickandmortyapi.com/api/episode/8"))),
    url = Url("https://rickandmortyapi.com/api/character/20"),
    created = ItemCreated(Instant.parse("2017-11-04T22:34:53.659Z"))
)

public val listOfTwoItems: List<Item> = listOf(fixtureItem1, fixtureItem2)
public val listOfMultipleItems: List<Item> = listOf(
    fixtureItem1,
    fixtureItem2,
    fixtureItem3,
    fixtureItem4,
    fixtureItem5,
    fixtureItem6,
    fixtureItem7,
    fixtureItem8,
    fixtureItem9,
    fixtureItem10,
    fixtureItem11,
    fixtureItem12,
    fixtureItem13,
    fixtureItem14,
    fixtureItem15,
    fixtureItem16,
    fixtureItem17,
    fixtureItem18,
    fixtureItem19,
    fixtureItem20,
)
