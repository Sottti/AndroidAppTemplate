package com.sottti.android.app.template.presentation.items.list.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.fixtures.listOfTwoItems
import org.junit.Test

internal class ItemsListMapperTest {

    @Test
    fun `given a domain Item, when mapped to UI model, then it should map all fields correctly`() {
        val domainItem = fixtureItem1

        val uiModel = domainItem.toUi()

        assertThat(uiModel.id).isEqualTo(domainItem.id.value)
        assertThat(uiModel.name).isEqualTo(domainItem.name.value)
        assertThat(uiModel.imageUrl).isEqualTo(domainItem.image.imageUrl)
        assertThat(uiModel.description).isEqualTo(domainItem.image.description)
    }

    @Test
    fun `given a list of domain Items, when mapped to UI models, then it should return a list of correctly mapped models`() {
        val domainList = listOfTwoItems

        val uiModelList = domainList.toUi()

        assertThat(uiModelList).hasSize(2)

        assertThat(uiModelList[0].id).isEqualTo(domainList[0].id.value)
        assertThat(uiModelList[0].name).isEqualTo(domainList[0].name.value)

        assertThat(uiModelList[1].id).isEqualTo(domainList[1].id.value)
        assertThat(uiModelList[1].name).isEqualTo(domainList[1].name.value)
    }

    @Test
    fun `given an empty list of domain Items, when mapped to UI models, then it should return an empty list`() {
        val emptyDomainList = emptyList<com.sottti.android.app.template.model.Item>()

        val uiModelList = emptyDomainList.toUi()

        assertThat(uiModelList).isEmpty()
    }
}
