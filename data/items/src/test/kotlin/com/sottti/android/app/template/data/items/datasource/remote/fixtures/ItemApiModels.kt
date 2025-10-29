package com.sottti.android.app.template.data.items.datasource.remote.fixtures

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.fixtures.fixtureItem
import com.sottti.android.app.template.fixtures.fixtureItem2

internal val itemApiModel = ItemApiModel(
    id = fixtureItem.id.value,
    name = fixtureItem.name.value,
    description = fixtureItem.image.description.value,
)

internal val item2ApiModel = ItemApiModel(
    id = fixtureItem2.id.value,
    name = fixtureItem2.name.value,
    description = fixtureItem2.image.description.value,
)
