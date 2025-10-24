package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.item.detail.feature.ui.ItemDetailUi
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.PullyListFeature
import com.sottti.android.app.template.presentation.pully.list.feature.ui.PullyListFeatureUi

@Composable
@ReadOnlyComposable
internal fun navigationEntries(): EntryProvider<NavKey> = { key ->
    when (key as NavigationDestination) {
        ItemDetailFeature -> NavEntry(key) { ItemDetailUi() }
        PullyListFeature -> NavEntry(key) { PullyListFeatureUi() }
    }
}
