package com.sottti.android.app.template.presentation.navigation.impl.fakes

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.ItemDetailFeature
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.PullyListFeature

internal const val ITEM_DETAIL_FEATURE_TEST_TAG = "itemDetailFeatureTestTag"
internal const val PULLY_LIST_FEATURE_TEST_TAG = "pullyListFeatureTestTag"

internal fun fakeNavigationEntries(): (NavKey) -> NavEntry<NavKey> =
    entryProvider {
        entry<PullyListFeature> { TaggedComposable(PULLY_LIST_FEATURE_TEST_TAG) }
        entry<ItemDetailFeature> { TaggedComposable(ITEM_DETAIL_FEATURE_TEST_TAG) }
    }

@Composable
internal fun TaggedComposable(
    tag: String,
    content: @Composable () -> Unit = {},
) {
    Box(modifier = Modifier.testTag(tag)) {
        content()
    }
}
