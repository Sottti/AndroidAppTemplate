package com.sottti.android.app.template.presentation.navigation.impl.manager

import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val commandsChannel = Channel<NavigationCommand>(Channel.BUFFERED)
    override fun commands(): Flow<NavigationCommand> = commandsChannel.receiveAsFlow()

    public override fun navigateTo(destination: NavigationDestination) {
        commandsChannel.trySend(NavigationCommand.NavigateTo(destination))
    }

    public override fun navigateBack() {
        commandsChannel.trySend(NavigationCommand.NavigateBack)
    }

    public override fun navigateToRoot(rootDestination: NavigationDestination) {
        commandsChannel.trySend(NavigationCommand.NavigateToRoot(rootDestination))
    }
}
