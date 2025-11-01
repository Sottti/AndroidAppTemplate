package com.sottti.android.app.template.presentation.item.details.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.fixtures.fixtureItem1
import com.sottti.android.app.template.fixtures.fixtureItem2
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import org.junit.Test

internal class ItemDetailsReducerTest {

    @Test
    fun `given a loading state, when reduce is called, then it returns a loaded state`() {
        val loadingState = initialState
        val item = fixtureItem1

        val newState = loadingState.reduce(item)

        assertThat(newState).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = newState as ItemDetailsState.Loaded

        assertThat(loadedState.topBarState.title).isEqualTo(item.name.value)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(item.name.value)
        assertThat(loadedState.item.identity.tagline.trailing).isEqualTo(item.tagline.value)
        assertThat(loadedState.item.identity.year.trailing).isEqualTo(item.year.value)
        assertThat(loadedState.item.imageState.imageUrl).isEqualTo(item.image.imageUrl)
        assertThat(loadedState.item.imageState.imageDescription).isEqualTo(item.image.description)
    }

    @Test
    fun `given an error state, when reduce is called, then it returns a loaded state`() {
        val errorState = ItemDetailsState.Error(topBarState = initialState.topBarState)
        val item = fixtureItem1

        val newState = errorState.reduce(item)

        assertThat(newState).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = newState as ItemDetailsState.Loaded

        assertThat(loadedState.topBarState.title).isEqualTo(item.name.value)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(item.name.value)
        assertThat(loadedState.item.identity.tagline.trailing).isEqualTo(item.tagline.value)
        assertThat(loadedState.item.identity.year.trailing).isEqualTo(item.year.value)
        assertThat(loadedState.item.imageState.imageUrl).isEqualTo(item.image.imageUrl)
        assertThat(loadedState.item.imageState.imageDescription).isEqualTo(item.image.description)
    }

    @Test
    fun `given a loaded state, when reduce is called with a new item, then it returns an updated loaded state`() {
        val initialLoadedState = initialState.reduce(fixtureItem1)
        val newItem = fixtureItem2

        val newLoadedState = initialLoadedState.reduce(newItem)

        assertThat(newLoadedState).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = newLoadedState as ItemDetailsState.Loaded

        assertThat(loadedState.topBarState.title).isEqualTo(newItem.name.value)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(newItem.name.value)
        assertThat(loadedState.item.identity.tagline.trailing).isEqualTo(newItem.tagline.value)
        assertThat(loadedState.item.identity.year.trailing).isEqualTo(newItem.year.value)
        assertThat(loadedState.item.imageState.imageUrl).isEqualTo(newItem.image.imageUrl)
        assertThat(loadedState.item.imageState.imageDescription).isEqualTo(newItem.image.description)
    }
}
