package com.sottti.android.app.template.presentation.home.data

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.domain.settings.usecase.GetSystemColorContrastFake
import com.sottti.android.app.template.domain.settings.usecase.GetSystemThemeFake
import com.sottti.android.app.template.domain.settings.usecase.ObserveDynamicColorFake
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemColorContrastFake
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemThemeFake
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class HomeViewModelTest {

    private lateinit var getSystemColorContrast: GetSystemColorContrastFake
    private lateinit var getSystemTheme: GetSystemThemeFake
    private lateinit var observeDynamicColor: ObserveDynamicColorFake
    private lateinit var observeSystemColorContrast: ObserveSystemColorContrastFake
    private lateinit var observeSystemTheme: ObserveSystemThemeFake

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        getSystemColorContrast = GetSystemColorContrastFake()
        getSystemTheme = GetSystemThemeFake()
        observeDynamicColor = ObserveDynamicColorFake()
        observeSystemColorContrast = ObserveSystemColorContrastFake()
        observeSystemTheme = ObserveSystemThemeFake()
    }

    @Test
    fun `when created, then initialState is calculated correctly from 'Get' use cases`() {
        getSystemColorContrast.setContrast(SystemColorContrast.HighContrast)
        getSystemTheme.setTheme(SystemTheme.DarkSystemTheme)

        viewModel = createViewModel()

        val state = viewModel.initialState
        assertThat(state.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
        assertThat(state.systemTheme).isEqualTo(SystemTheme.DarkSystemTheme)
        assertThat(state.dynamicColor).isEqualTo(DynamicColor(enabled = false))
    }

    @Test
    fun `when state flow is collected, then it emits the combined initial state`() = runTest {
        observeSystemColorContrast.emit(SystemColorContrast.StandardContrast)
        observeSystemTheme.emit(SystemTheme.LightSystemTheme)
        observeDynamicColor.emit(DynamicColor(enabled = false))

        viewModel = createViewModel()

        viewModel.state.test {
            val firstState = awaitItem()
            assertThat(firstState.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
            assertThat(firstState.systemTheme).isEqualTo(SystemTheme.LightSystemTheme)
            assertThat(firstState.dynamicColor.enabled).isFalse()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when an observed use case emits a new value, then state flow is updated`() = runTest {
        observeSystemColorContrast.emit(SystemColorContrast.StandardContrast)
        observeSystemTheme.emit(SystemTheme.LightSystemTheme)
        observeDynamicColor.emit(DynamicColor(enabled = false))
        viewModel = createViewModel()

        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState.systemColorContrast).isEqualTo(SystemColorContrast.StandardContrast)
            assertThat(initialState.dynamicColor.enabled).isFalse()

            observeSystemColorContrast.emit(SystemColorContrast.HighContrast)

            val contrastUpdatedState = awaitItem()
            assertThat(contrastUpdatedState.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
            assertThat(contrastUpdatedState.systemTheme).isEqualTo(SystemTheme.LightSystemTheme)
            assertThat(contrastUpdatedState.dynamicColor.enabled).isFalse()

            observeDynamicColor.emit(DynamicColor(enabled = true))

            val dynamicColorUpdatedState = awaitItem()
            assertThat(dynamicColorUpdatedState.systemColorContrast).isEqualTo(SystemColorContrast.HighContrast)
            assertThat(dynamicColorUpdatedState.dynamicColor.enabled).isTrue()
        }
    }

    @Test
    fun `when system theme use case changes, then it updates the state accordingly`() = runTest {
        observeSystemColorContrast.emit(SystemColorContrast.StandardContrast)
        observeSystemTheme.emit(SystemTheme.LightSystemTheme)
        observeDynamicColor.emit(DynamicColor(false))
        viewModel = createViewModel()

        viewModel.state.test {
            assertThat(awaitItem().systemTheme).isEqualTo(SystemTheme.LightSystemTheme)

            observeSystemTheme.emit(SystemTheme.DarkSystemTheme)
            assertThat(awaitItem().systemTheme).isEqualTo(SystemTheme.DarkSystemTheme)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when re-emitting the same values, then no new state is produced`() = runTest {
        observeSystemColorContrast.emit(SystemColorContrast.StandardContrast)
        observeSystemTheme.emit(SystemTheme.LightSystemTheme)
        observeDynamicColor.emit(DynamicColor(false))
        viewModel = createViewModel()

        viewModel.state.test {
            awaitItem()
            observeSystemColorContrast.emit(SystemColorContrast.StandardContrast)
            observeSystemTheme.emit(SystemTheme.LightSystemTheme)
            observeDynamicColor.emit(DynamicColor(false))
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun createViewModel(): HomeViewModel =
        HomeViewModel(
            getSystemColorContrast = getSystemColorContrast,
            getSystemTheme = getSystemTheme,
            observeDynamicColor = observeDynamicColor,
            observeSystemColorContrast = observeSystemColorContrast,
            observeSystemTheme = observeSystemTheme,
        )
}
