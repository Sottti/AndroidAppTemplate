package com.sottti.android.app.template.presentation.navigation.impl.manager

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.sottti.android.app.template.presentation.navigation.model.NavigationCommand
import com.sottti.android.app.template.presentation.navigation.model.NavigationDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class NavigationManagerImplTest {

    private lateinit var manager: NavigationManagerImpl

    @Before
    fun setUp() {
        manager = NavigationManagerImpl()
    }

    @Test
    fun `no commands are emitted without actions`() = runTest {
        manager.commands().test {
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `when navigate to is called, then a navigate to command should be emitted`() = runTest {
        val destination = NavigationDestination.ItemsList

        manager.commands().test {
            expectNoEvents()
            manager.navigateTo(destination)
            val emittedCommand = awaitItem()
            Truth.assertThat(emittedCommand).isEqualTo(NavigationCommand.NavigateTo(destination))
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when navigate back is called, then a navigate back command should be emitted`() = runTest {
        manager.commands().test {
            expectNoEvents()
            manager.navigateBack()
            Truth.assertThat(awaitItem()).isEqualTo(NavigationCommand.NavigateBack)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when navigate to root is called, then a navigate to root command should be emitted`() =
        runTest {
            val rootDestination = NavigationDestination.ItemsList

            manager.commands().test {
                expectNoEvents()
                manager.navigateToRoot(rootDestination)
                Truth.assertThat(awaitItem())
                    .isEqualTo(NavigationCommand.NavigateToRoot(rootDestination))
                expectNoEvents()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `when multiple navigation methods are called, then commands should be emitted in the correct order`() =
        runTest {
            val firstDestination = NavigationDestination.ItemsList
            val secondDestination = NavigationDestination.ItemDetail

            manager.commands().test {
                expectNoEvents()
                manager.navigateTo(firstDestination)
                manager.navigateTo(secondDestination)
                manager.navigateBack()

                Truth.assertThat(awaitItem())
                    .isEqualTo(NavigationCommand.NavigateTo(firstDestination))
                Truth.assertThat(awaitItem())
                    .isEqualTo(NavigationCommand.NavigateTo(secondDestination))
                Truth.assertThat(awaitItem()).isEqualTo(NavigationCommand.NavigateBack)

                expectNoEvents()
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `burst of commands preserves order`() = runTest {
        val destinations = List(50) { NavigationDestination.ItemsList }
        manager.commands().test {
            expectNoEvents()
            destinations.forEach { manager.navigateTo(it) }
            repeat(destinations.size) {
                Truth.assertThat(awaitItem())
                    .isEqualTo(NavigationCommand.NavigateTo(NavigationDestination.ItemsList))
            }
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `events go to a single collector (competing consumers)`() = runTest {
        val channel1 = mutableListOf<NavigationCommand>()
        val channel2 = mutableListOf<NavigationCommand>()
        val job1 = launch { manager.commands().collect { channel1.add(it) } }
        val job2 = launch { manager.commands().collect { channel2.add(it) } }

        manager.navigateBack()
        advanceUntilIdle()

        Truth.assertThat((channel1.size + channel2.size)).isEqualTo(1)
        job1.cancel()
        job2.cancel()
    }
}
