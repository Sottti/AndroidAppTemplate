package com.sottti.android.app.template.data.items.datasource.remote.fixtures

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemLocationApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemsApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.PageInfoApiModel
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem2
import com.sottti.android.app.template.domain.items.model.Item
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
private fun Item.toApiModel(): ItemApiModel =
    ItemApiModel(
        id = id.value,
        name = name.value,
        status = status?.value.orEmpty(),
        species = species?.value.orEmpty(),
        type = type?.value.orEmpty(),
        gender = gender?.value.orEmpty(),
        origin = ItemLocationApiModel(
            name = origin?.name?.value.orEmpty(),
            url = origin?.url?.value.orEmpty(),
        ),
        location = ItemLocationApiModel(
            name = location?.name?.value.orEmpty(),
            url = location?.url?.value.orEmpty(),
        ),
        image = image?.url?.value.orEmpty(),
        episode = episodes?.map { episode -> episode.value.value }.orEmpty(),
        url = url?.value.orEmpty(),
        created = created?.value?.toString().orEmpty(),
    )

internal val fixtureItem1ApiModel = fixtureItem1.toApiModel()

internal val fixtureItem2ApiModel = fixtureItem2.toApiModel()

internal val listOfTwoApiModels = listOf(
    fixtureItem1ApiModel,
    fixtureItem2ApiModel,
)

internal val fixtureItemsApiModel = ItemsApiModel(
    info = PageInfoApiModel(
        count = listOfTwoApiModels.size,
        pages = 1,
        next = null,
        prev = null,
    ),
    results = listOfTwoApiModels,
)
