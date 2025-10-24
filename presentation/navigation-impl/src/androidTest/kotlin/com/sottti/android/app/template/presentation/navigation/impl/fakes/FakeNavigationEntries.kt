package com.sottti.android.app.template.presentation.navigation.impl.fakes


import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.sottti.android.app.template.presentation.navigation.impl.EntryProvider
import com.sottti.android.app.template.presentation.navigation.impl.TaggedComposable
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.PullyListFeature

internal const val ITEM_DETAIL_FEATURE_TEST_TAG = "itemDetailFeatureTestTag"
internal const val PULLY_LIST_FEATURE_TEST_TAG = "pullyListFeatureTestTag"

internal fun fakeNavigationEntries(): EntryProvider<NavKey> = { key ->
    when (key as NavigationDestination) {
        ItemDetailFeature -> NavEntry(key) { TaggedComposable(PULLY_LIST_FEATURE_TEST_TAG) }
        PullyListFeature -> NavEntry(key) { TaggedComposable(ITEM_DETAIL_FEATURE_TEST_TAG) }
    }
}
