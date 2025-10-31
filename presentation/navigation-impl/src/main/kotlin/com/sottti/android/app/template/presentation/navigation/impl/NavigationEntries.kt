package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.item.details.ui.ItemDetails
import com.sottti.android.app.template.presentation.items.list.ui.ItemList
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetail
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemsList

@Composable
@ReadOnlyComposable
internal fun navigationEntries(): EntryProvider<NavKey> = { key ->
    when (key as NavigationDestination) {
        ItemsList -> NavEntry(key) { ItemList() }
        is ItemDetail -> NavEntry(key = key) {
            val itemId: Int = (key as ItemDetail).itemId
            ItemDetails(itemId)
        }
    }
}
