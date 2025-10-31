package com.sottti.android.app.template.presentation.navigation.impl.fakes


import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.navigation.impl.EntryProvider
import com.sottti.android.app.template.presentation.navigation.impl.TaggedComposable
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetail
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemsList

internal const val ITEM_DETAIL_FEATURE_TEST_TAG = "itemDetailFeatureTestTag"
internal const val PULLY_LIST_FEATURE_TEST_TAG = "pullyListFeatureTestTag"

internal fun fakeNavigationEntries(): EntryProvider<NavKey> = { key ->
    when (key as NavigationDestination) {
        ItemsList -> NavEntry(key) { TaggedComposable(ITEM_DETAIL_FEATURE_TEST_TAG) }
        is ItemDetail -> NavEntry(key) { TaggedComposable(PULLY_LIST_FEATURE_TEST_TAG) }
    }
}
