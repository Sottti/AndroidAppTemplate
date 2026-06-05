package com.sottti.android.app.template.presentation.navigation.model

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Immutable
public sealed interface NavigationDestination : NavKey {
    @Immutable
    @Serializable
    public data object CharactersList : NavigationDestination

    @Immutable
    @Serializable
    public data class CharacterDetail(val characterId: Int) : NavigationDestination
}
