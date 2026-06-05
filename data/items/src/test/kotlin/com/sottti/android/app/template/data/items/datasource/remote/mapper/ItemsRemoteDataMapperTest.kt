package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItem1ApiModel
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItem2ApiModel
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.fixtureItemsApiModel
import com.sottti.android.app.template.data.items.datasource.remote.fixtures.listOfTwoApiModels
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemLocationApiModel
import org.junit.Test

internal class ItemsRemoteDataMapperTest {

    @Test
    fun `given a standard API model, when mapped to domain, then it should map all fields correctly`() {
        val apiModel = fixtureItem1ApiModel

        val domainModel = apiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(apiModel.id)
        assertThat(domainModel.name.value).isEqualTo(apiModel.name)
        assertThat(domainModel.status?.value).isEqualTo(apiModel.status)
        assertThat(domainModel.species?.value).isEqualTo(apiModel.species)
        assertThat(domainModel.gender?.value).isEqualTo(apiModel.gender)
        assertThat(domainModel.origin?.name?.value).isEqualTo(apiModel.origin.name)
        assertThat(domainModel.origin?.url?.value).isEqualTo(apiModel.origin.url)
        assertThat(domainModel.location?.name?.value).isEqualTo(apiModel.location.name)
        assertThat(domainModel.location?.url?.value).isEqualTo(apiModel.location.url)
        assertThat(domainModel.image?.description?.value).isEqualTo("An image of ${apiModel.name}")
        assertThat(domainModel.image?.url?.value).isEqualTo(apiModel.image)
        assertThat(domainModel.episodes?.map { episode -> episode.value.value })
            .containsExactlyElementsIn(apiModel.episode)
            .inOrder()
        assertThat(domainModel.url?.value).isEqualTo(apiModel.url)
        assertThat(domainModel.created?.value.toString()).isEqualTo(apiModel.created)
    }

    @Test
    fun `given blank optional API fields, when mapped to domain, then nullable domain fields are null`() {
        val apiModel = ItemApiModel(
            id = 331,
            name = "No Extras Rick",
            status = "",
            species = "",
            type = "",
            gender = "",
            origin = ItemLocationApiModel(name = "", url = ""),
            location = ItemLocationApiModel(name = "", url = ""),
            image = "",
            episode = listOf("", "https://rickandmortyapi.com/api/episode/1"),
            url = "",
            created = "",
        )

        val domainModel = apiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(331)
        assertThat(domainModel.status).isNull()
        assertThat(domainModel.species).isNull()
        assertThat(domainModel.type).isNull()
        assertThat(domainModel.gender).isNull()
        assertThat(domainModel.origin).isNull()
        assertThat(domainModel.location).isNull()
        assertThat(domainModel.image).isNull()
        assertThat(domainModel.episodes?.map { episode -> episode.value.value })
            .containsExactly("https://rickandmortyapi.com/api/episode/1")
        assertThat(domainModel.url).isNull()
        assertThat(domainModel.created).isNull()
    }

    @Test
    fun `given paged API models, when mapped to domain, then it should map all items in the list`() {
        val apiModelList = fixtureItemsApiModel

        val domainList = apiModelList.toDomain()

        assertThat(domainList).hasSize(2)
        assertThat(domainList[0].id.value).isEqualTo(fixtureItem1ApiModel.id)
        assertThat(domainList[1].id.value).isEqualTo(fixtureItem2ApiModel.id)
        assertThat(domainList[1].name.value).isEqualTo(fixtureItem2ApiModel.name)
        assertThat(domainList.map { item -> item.id.value })
            .containsExactlyElementsIn(listOfTwoApiModels.map { item -> item.id })
            .inOrder()
    }
}
