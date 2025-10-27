package com.sottti.android.app.template.data.items.datasource.local.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sottti.android.app.template.domain.core.models.ImageContentDescription
import com.sottti.android.app.template.domain.core.models.ImageUrl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.model.ItemId
import com.sottti.android.app.template.model.ItemImage
import com.sottti.android.app.template.model.ItemName

internal fun createPagingState(
    pages: Int,
    pageSize: Int,
): PagingState<Int, Item> = PagingState(
    pages = List(pages) { pageIndex ->
        PagingSource.LoadResult.Page(
            data = List(pageSize) { itemIndex -> createItem(itemIndex) },
            prevKey = if (pageIndex == 0) null else pageIndex,
            // Keys are 1-based, index is 0-based
            nextKey = if (pageIndex < pages - 1) pageIndex + 2 else null
        )
    },
    anchorPosition = null,
    config = PagingConfig(pageSize = pageSize),
    leadingPlaceholderCount = 0
)

private fun createItem(index: Int): Item =
    Item(
        id = ItemId("item-$index"),
        name = ItemName("Test Item Name $index"),
        image = ItemImage(
            description = ImageContentDescription("Description for test image $index"),
            imageUrl = ImageUrl("http://example.com/image$index.png"),
        ),
    )
