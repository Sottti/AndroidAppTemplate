package com.sottti.android.app.template.presentation.navigation.manager

import androidx.compose.runtime.Immutable
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import kotlinx.coroutines.flow.Flow

@Immutable
public interface NavigationManager {
    public fun commands(): Flow<NavigationCommand>
    public fun navigateBack()
    public fun navigateTo(destination: NavigationDestination)
    public fun navigateToRoot(rootDestination: NavigationDestination)
}
