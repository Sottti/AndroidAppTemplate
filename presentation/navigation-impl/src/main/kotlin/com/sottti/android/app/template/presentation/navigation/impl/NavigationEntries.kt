package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.item.detail.ui.ItemDetailUi
import com.sottti.android.app.template.presentation.items.list.ui.ItemListUi
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetail
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemsList

@Composable
@ReadOnlyComposable
internal fun navigationEntries(): EntryProvider<NavKey> = { key ->
    when (key as NavigationDestination) {
        ItemsList -> NavEntry(key) { ItemListUi() }
        is ItemDetail -> NavEntry(key = key) { ItemDetailUi() }
    }
}
