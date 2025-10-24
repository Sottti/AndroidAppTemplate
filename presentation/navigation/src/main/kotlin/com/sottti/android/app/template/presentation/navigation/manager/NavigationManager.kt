package com.sottti.android.app.template.presentation.navigation.manager

import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import kotlinx.coroutines.flow.Flow

public interface NavigationManager {
    public fun commands(): Flow<NavigationCommand>
    public fun navigateBack()
    public fun navigateTo(destination: NavigationDestination)
}
