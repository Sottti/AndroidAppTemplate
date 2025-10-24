package com.sottti.android.app.template.presentation.navigation.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sottti.android.app.template.presentation.design.system.colors.color.compositionLocal.colors
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateBack
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateTo
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand.NavigateToRoot
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination.PullyListFeature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
public fun Navigator(
    navigationManager: NavigationManager,
    entryProvider: EntryProvider<NavKey>? = null,
) {
    val backStack = rememberNavBackStack(PullyListFeature)
    LaunchedEffect(key1 = navigationManager) {
        observeCommandsInScope(
            backStack = backStack,
            navigationManager = navigationManager,
            scope = this,
        )
    }
    NavDisplay(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider ?: navigationEntries(),
    )
}

private fun observeCommandsInScope(
    backStack: NavBackStack<NavKey>,
    navigationManager: NavigationManager,
    scope: CoroutineScope,
) = navigationManager.commands().onEach { command ->
    when (command) {
        is NavigateTo -> if (backStack.lastOrNull() != command.destination) {
            backStack.add(command.destination)
        }

        NavigateBack -> if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }

        is NavigateToRoot -> {
            backStack.clear()
            backStack.add(command.rootDestination)
        }
    }
}
    .launchIn(scope)
