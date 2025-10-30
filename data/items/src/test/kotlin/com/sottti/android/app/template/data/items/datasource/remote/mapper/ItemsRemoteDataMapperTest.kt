package com.sottti.android.app.template.data.items.datasource.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.remote.model.ItemApiModel
import org.junit.Test

internal class ItemsRemoteDataMapperTest {

    @Test
    fun `given a standard API model, when mapped to domain, then it should map all fields correctly`() {
        val apiModel = ItemApiModel(
            id = 1,
            name = "Test Beer",
            description = "A standard test beer."
        )

        val domainModel = apiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(1)
        assertThat(domainModel.name.value).isEqualTo("Test Beer")
        assertThat(domainModel.image.description.value).isEqualTo("A standard test beer.")
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/1/400")
    }

    @Test
    fun `given the specific 'Lime Zephyr' item with ID 331, when mapped to domain, then its ID should remain 331`() {
        val limeZephyrApiModel = ItemApiModel(
            id = 331,
            name = "Lime Zephyr V2 (Fanzine)",
            description = "A special case beer."
        )

        val domainModel = limeZephyrApiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(331)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/331/400")
    }

    @Test
    fun `given a non 'Lime Zephyr' item with ID 331, when mapped to domain, then its ID should be remapped to 332`() {
        val otherItemWithId331 = ItemApiModel(
            id = 331,
            name = "Not Lime Zephyr",
            description = "Another beer with a conflicting ID."
        )

        val domainModel = otherItemWithId331.toDomain()

        assertThat(domainModel.id.value).isEqualTo(332)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/331/400")
    }

    @Test
    fun `given an API model with an ID greater than 331, when mapped to domain, then its ID should be incremented by one`() {
        val highIdApiModel = ItemApiModel(
            id = 400,
            name = "High ID Beer",
            description = "A beer with a high ID."
        )

        val domainModel = highIdApiModel.toDomain()

        assertThat(domainModel.id.value).isEqualTo(401)
        assertThat(domainModel.image.imageUrl.value).isEqualTo("https://picsum.photos/id/400/400")
    }

    @Test
    fun `given a list of API models, when mapped to domain, then it should map all items in the list`() {
        val apiModelList = listOf(
            ItemApiModel(id = 1, name = "Item 1", description = "Desc 1"),
            ItemApiModel(id = 2, name = "Item 2", description = "Desc 2")
        )

        val domainList = apiModelList.toDomain()

        assertThat(domainList).hasSize(2)
        assertThat(domainList[0].id.value).isEqualTo(1)
        assertThat(domainList[1].id.value).isEqualTo(2)
        assertThat(domainList[1].name.value).isEqualTo("Item 2")
    }
}
