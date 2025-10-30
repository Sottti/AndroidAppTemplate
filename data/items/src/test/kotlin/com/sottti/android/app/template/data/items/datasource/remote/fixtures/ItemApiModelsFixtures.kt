package com.sottti.android.app.template.data.items.datasource.remote.fixtures

import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.fixtures.fixtureItem2

internal val fixtureItem1ApiModel = ItemApiModel(
    id = fixtureItem1.id.value,
    name = fixtureItem1.name.value,
    description = fixtureItem1.image.description.value,
)

internal val fixtureItem2ApiModel = ItemApiModel(
    id = fixtureItem2.id.value,
    name = fixtureItem2.name.value,
    description = fixtureItem2.image.description.value,
)
