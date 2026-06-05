package com.sottti.android.app.template.domain.characters.usecase

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.characters.fixtures.listOfTwoCharacters
import com.sottti.android.app.template.domain.characters.model.Character
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveCharactersFakeTest {

    private lateinit var observeCharacters: ObserveCharactersFake

    @Before
    fun setUp() {
        observeCharacters = ObserveCharactersFake()
    }

    @Test
    fun `given no characters are set, when invoked, then it should return an empty paging data flow`() =
        runTest {
            val resultFlow = observeCharacters()
            val snapshot = resultFlow.asSnapshot()

            assertThat(snapshot).isEmpty()
        }

    @Test
    fun `given a specific flow is set, when invoked, then it should return that exact flow`() =
        runTest {
            val pagingData = PagingData.from(listOfTwoCharacters)
            val specificFlow = flowOf(pagingData)
            observeCharacters.setCharacters(specificFlow)

            val resultFlow = observeCharacters()
            val snapshot = resultFlow.asSnapshot()

            assertThat(resultFlow).isEqualTo(specificFlow)
            assertThat(snapshot).hasSize(2)
            assertThat(snapshot[0].name?.value).isEqualTo(listOfTwoCharacters[0].name?.value)
        }

    @Test
    fun `set characters called twice replaces previous flow`() = runTest {
        val first = flowOf(PagingData.empty<Character>())
        val second = flowOf(PagingData.from(listOfTwoCharacters))
        observeCharacters.setCharacters(first)
        observeCharacters.setCharacters(second)

        val result = observeCharacters()
        assertThat(result).isSameInstanceAs(second)
        assertThat(result.asSnapshot()).hasSize(2)
    }

    @Test
    fun `invoke returns the exact same flow instance that was set`() = runTest {
        val provided = flowOf(PagingData.from(listOfTwoCharacters))
        observeCharacters.setCharacters(provided)
        val result = observeCharacters()
        assertThat(result).isSameInstanceAs(provided)
    }
}
