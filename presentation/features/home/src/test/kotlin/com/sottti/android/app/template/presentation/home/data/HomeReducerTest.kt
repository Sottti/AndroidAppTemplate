package com.sottti.android.app.template.presentation.home.data

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.core.models.DynamicColor
import com.sottti.android.app.template.domain.core.models.SystemColorContrast
import com.sottti.android.app.template.domain.core.models.SystemTheme
import com.sottti.android.app.template.presentation.home.model.HomeState
import org.junit.Test

internal class HomeReducerTest {

    @Test
    fun `given a home state, when reduce is called with new settings, then it returns an updated state`() {
        val initialState = HomeState(
            dynamicColor = DynamicColor(enabled = false),
            systemColorContrast = SystemColorContrast.StandardContrast,
            systemTheme = SystemTheme.LightSystemTheme,
        )

        val newDynamicColor = DynamicColor(enabled = true)
        val newContrast = SystemColorContrast.HighContrast
        val newTheme = SystemTheme.DarkSystemTheme

        val newState = initialState.reduce(
            dynamicColor = newDynamicColor,
            systemColorContrast = newContrast,
            systemTheme = newTheme,
        )

        assertThat(newState.dynamicColor).isEqualTo(newDynamicColor)
        assertThat(newState.systemColorContrast).isEqualTo(newContrast)
        assertThat(newState.systemTheme).isEqualTo(newTheme)

        assertThat(newState).isNotSameInstanceAs(initialState)
    }

    @Test
    fun `given a home state, when reduce is called with the same settings, then it returns an equal but new state`() {
        val initialState = HomeState(
            dynamicColor = DynamicColor(enabled = true),
            systemColorContrast = SystemColorContrast.MediumContrast,
            systemTheme = SystemTheme.DarkSystemTheme,
        )

        val newState = initialState.reduce(
            dynamicColor = initialState.dynamicColor,
            systemColorContrast = initialState.systemColorContrast,
            systemTheme = initialState.systemTheme,
        )

        assertThat(newState).isEqualTo(initialState)

        assertThat(newState).isNotSameInstanceAs(initialState)
    }
}
