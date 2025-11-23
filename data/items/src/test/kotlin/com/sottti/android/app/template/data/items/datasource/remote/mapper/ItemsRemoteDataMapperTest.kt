package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItem1ApiModel
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItem2ApiModel
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.listOfTwoApiModels
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import org.junit.Test

internal class ItemsRemoteDataMapperTest {

    @Test
    fun `given a standard API model, when mapped to domain, then it should map all fields correctly`() {
        val apiModel = fixtureItem1ApiModel

        val domainModel = apiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(apiModel.id)
        assertThat(domainModel.name.value).isEqualTo(apiModel.name)
        assertThat(domainModel.tagline.value).isEqualTo(apiModel.tagline)
        assertThat(domainModel.year.value).isEqualTo(apiModel.year)
        assertThat(domainModel.image.description.value).isEqualTo("An image description")
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/${apiModel.id}/600")
    }

    @Test
    fun `given the specific 'Lime Zephyr' item with ID 331, when mapped to domain, then its ID should remain 331`() {
        val limeZephyrApiModel = ItemApiModel(
            id = 331,
            name = "Lime Zephyr V2 (Fanzine)",
            tagline = "A special case beer.",
            year = "2023",
        )

        val domainModel = limeZephyrApiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(331)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/331/600")
    }

    @Test
    fun `given a non 'Lime Zephyr' item with ID 331, when mapped to domain, then its ID should be remapped to 332`() {
        val otherItemWithId331 = ItemApiModel(
            id = 331,
            name = "Not Lime Zephyr",
            tagline = "Another beer with a conflicting ID.",
            year = "2024",
        )

        val domainModel = otherItemWithId331.toDomain()

        assertThat(domainModel.id.value).isEqualTo(332)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/331/600")
    }

    @Test
    fun `given an API model with an ID greater than 331, when mapped to domain, ID should be incremented by one`() {
        val highIdApiModel = ItemApiModel(
            id = 400,
            name = "High ID Beer",
            tagline = "A beer with a high ID.",
            year = "2025",
        )

        val domainModel = highIdApiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(401)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/400/600")
    }

    @Test
    fun `given a list of API models, when mapped to domain, then it should map all items in the list`() {
        val apiModelList = listOfTwoApiModels

        val domainList = apiModelList.toDomain()

        assertThat(domainList).hasSize(2)
        assertThat(domainList[0].id.value).isEqualTo(fixtureItem1ApiModel.id)
        assertThat(domainList[1].id.value).isEqualTo(fixtureItem2ApiModel.id)
        assertThat(domainList[1].name.value).isEqualTo(fixtureItem2ApiModel.name)
    }
}
