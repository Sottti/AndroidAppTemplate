package com.sottti.android.app.template.presentation.navigation.impl.fakes

import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

internal class FakeNavigationManager : NavigationManager {
    private val _commands = Channel<NavigationCommand>(Channel.BUFFERED)

    override fun commands(): Flow<NavigationCommand> = _commands.receiveAsFlow()

    override fun navigateBack() {
        _commands.trySend(NavigationCommand.NavigateBack)
    }

    override fun navigateTo(destination: NavigationDestination) {
        _commands.trySend(NavigationCommand.NavigateTo(destination))
    }

    fun navigateToRoot(destination: NavigationDestination) {
        _commands.trySend(NavigationCommand.NavigateToRoot(destination))
    }
}
