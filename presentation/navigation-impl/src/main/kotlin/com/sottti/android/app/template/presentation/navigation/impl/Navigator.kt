package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateBack
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateTo
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateToRoot
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.PullyListFeature
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
public fun Navigator(
    navigationManager: NavigationManager,
) {
    val backStack = rememberNavBackStack(PullyListFeature)
    observeCommands(
        backStack = backStack,
        navigationManager = navigationManager,
    )
    NavDisplay(
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider(),
    )
}

@Composable
private fun observeCommands(
    backStack: NavBackStack<NavKey>,
    navigationManager: NavigationManager,
) {
    LaunchedEffect(navigationManager, backStack) {
        navigationManager.commands().onEach { command ->
            when (command) {
                is NavigateTo -> {
                    if (backStack.lastOrNull() != command.destination) {
                        backStack.add(command.destination)
                    }
                }

                NavigateBack -> {
                    if (backStack.size > 1) {
                        backStack.removeLastOrNull()
                    }
                }

                is NavigateToRoot -> {
                    backStack.clear()
                    backStack.add(command.rootDestination)
                }
            }
        }
            .launchIn(this)
    }
}
