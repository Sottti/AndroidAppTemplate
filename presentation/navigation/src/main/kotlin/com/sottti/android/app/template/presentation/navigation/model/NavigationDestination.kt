package com.sottti.android.app.template.presentation.navigation.model

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Immutable
public sealed interface NavigationDestination : NavKey {
    @Immutable
    @Serializable
    public data object ItemsList : NavigationDestination

    @Immutable
    @Serializable
    public data class ItemDetail(val itemId: Int) : NavigationDestination
}
