package com.sottti.android.app.template.domain.items.usecase

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.domain.items.fixtures.listOfTwoItems
import com.sottti.android.app.template.domain.items.model.Item
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class ObserveItemsTestFake {

    private lateinit var observeItems: ObserveItemsFake

    @Before
    fun setUp() {
        observeItems = ObserveItemsFake()
    }

    @Test
    fun `given no items are set, when invoked, then it should return an empty paging data flow`() =
        runTest {

            val resultFlow = observeItems()
            val snapshot = resultFlow.asSnapshot()

            assertThat(snapshot).isEmpty()
        }

    @Test
    fun `given a specific flow is set, when invoked, then it should return that exact flow`() =
        runTest {
            val pagingData = PagingData.from(listOfTwoItems)
            val specificFlow = flowOf(pagingData)
            observeItems.setItems(specificFlow)

            val resultFlow = observeItems()
            val snapshot = resultFlow.asSnapshot()

            assertThat(resultFlow).isEqualTo(specificFlow)
            assertThat(snapshot).hasSize(2)
            assertThat(snapshot[0].name.value).isEqualTo(listOfTwoItems[0].name.value)
        }

    @Test
    fun `set items called twice replaces previous flow`() = runTest {
        val first = flowOf(PagingData.empty<Item>())
        val second = flowOf(PagingData.from(listOfTwoItems))
        observeItems.setItems(first)
        observeItems.setItems(second)

        val result = observeItems()
        assertThat(result).isSameInstanceAs(second)
        assertThat(result.asSnapshot()).hasSize(2)
    }

    @Test
    fun `invoke returns the exact same flow instance that was set`() = runTest {
        val provided = flowOf(PagingData.from(listOfTwoItems))
        observeItems.setItems(provided)
        val result = observeItems()
        assertThat(result).isSameInstanceAs(provided)
    }
}
