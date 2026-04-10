package com.sottti.android.app.template.presentation.item.details.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1MaxedNulls
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem2
import com.sottti.android.app.template.presentation.design.system.icons.data.Icons
import com.sottti.android.app.template.presentation.design.system.images.local.data.Images
import com.sottti.android.app.template.presentation.item.details.R
import com.sottti.android.app.template.presentation.item.details.model.ItemDetailsState
import com.sottti.android.app.template.presentation.item.details.model.ItemImageState
import com.sottti.android.app.template.presentation.item.details.ui.loadingState
import org.junit.Test

internal class ItemDetailsReducerTest {

    @Test
    fun `given a loading state, when update is item, then it returns a loaded state`() {
        val update = loadingState.reduce(fixtureItem1)

        assertThat(update).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = update as ItemDetailsState.Loaded

        assertThat(loadedState.item.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(fixtureItem1.name.value)
        assertThat(loadedState.item.image).isInstanceOf(ItemImageState.NetworkImage::class.java)
        val image = loadedState.item.image as ItemImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureItem1.image?.description)
        assertThat(image.url).isEqualTo(fixtureItem1.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem1.name.value)
    }

    @Test
    fun `given a loading state, when update is item with maxed nulls, then it returns a loaded state`() {
        val update = loadingState.reduce(fixtureItem1MaxedNulls)

        assertThat(update).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = update as ItemDetailsState.Loaded

        assertThat(loadedState.item.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(fixtureItem1MaxedNulls.name.value)
        assertThat(loadedState.item.image).isEqualTo(
            ItemImageState.PlaceholderImage(Images.AvatarPlaceholder.state)
        )
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem1MaxedNulls.name.value)
    }

    @Test
    fun `given an error state, when reduce is called, then it returns a loaded state`() {
        val errorState = ItemDetailsState.Error(topBarState = initialState.topBarState)
        val update = errorState.reduce(fixtureItem1)

        assertThat(update).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = update as ItemDetailsState.Loaded

        assertThat(loadedState.item.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(fixtureItem1.name.value)
        assertThat(loadedState.item.image).isInstanceOf(ItemImageState.NetworkImage::class.java)
        val image = loadedState.item.image as ItemImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureItem1.image?.description)
        assertThat(image.url).isEqualTo(fixtureItem1.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem1.name.value)
    }

    @Test
    fun `given a loaded state, when reduce is called with a new item, then it returns an updated loaded state`() {
        val initialLoadedState = initialState.reduce(fixtureItem1)
        val update = initialLoadedState.reduce(fixtureItem2)

        assertThat(update).isInstanceOf(ItemDetailsState.Loaded::class.java)
        val loadedState = update as ItemDetailsState.Loaded

        assertThat(loadedState.item.identity.header).isEqualTo(R.string.identity_header)
        assertThat(loadedState.item.identity.name.trailing).isEqualTo(fixtureItem2.name.value)
        assertThat(loadedState.item.image).isInstanceOf(ItemImageState.NetworkImage::class.java)
        val image = loadedState.item.image as ItemImageState.NetworkImage
        assertThat(image.description).isEqualTo(fixtureItem2.image?.description)
        assertThat(image.url).isEqualTo(fixtureItem2.image?.url)
        assertThat(loadedState.topBarState.navigationIcon).isEqualTo(Icons.Arrow.Back.filled)
        assertThat(loadedState.topBarState.title).isEqualTo(fixtureItem2.name.value)
    }
}
