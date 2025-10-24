package com.sottti.android.app.template.presentation.navigation.model

public sealed interface NavigationCommand {
    public data class NavigateTo(
        val destination: NavigationDestination,
    ) : NavigationCommand

    public data object NavigateBack : NavigationCommand
    public data class NavigateToRoot(
        val rootDestination: NavigationDestination,
    ) : NavigationCommand
}
