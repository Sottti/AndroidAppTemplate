package com.sottti.android.app.template.data.items.repository

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSourceFake
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSourceFake
import com.sottti.android.app.template.data.items.mediator.ItemsRemoteMediator
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1
import com.sottti.android.app.template.domain.items.fixtures.fixtureItem1MaxedNulls
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

internal class ItemsRepositoryImplTest {

    private lateinit var localDataSource: ItemsLocalDataSourceFake
    private lateinit var remoteDataSource: ItemsRemoteDataSourceFake
    private lateinit var repository: ItemsRepositoryImpl

    @Before
    fun setUp() {
        localDataSource = ItemsLocalDataSourceFake()
        remoteDataSource = ItemsRemoteDataSourceFake()
        repository = ItemsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            remoteMediator = ItemsRemoteMediator(
                localDataSource = localDataSource,
                remoteDataSource = remoteDataSource,
            ),
        )
    }

    @Test
    fun `observe item fetches remote item when local row is absent`() = runTest {
        remoteDataSource.setSuccessResponse(listOf(fixtureItem1))

        val item = repository.observeItem(fixtureItem1.id).first()

        assertThat(item).isEqualTo(fixtureItem1)
        assertThat(remoteDataSource.lastCalledItemId).isEqualTo(fixtureItem1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureItem1)
    }

    @Test
    fun `observe item refreshes expired cached item`() = runTest {
        localDataSource.upsert(fixtureItem1MaxedNulls)
        localDataSource.expiredIds.add(fixtureItem1.id)
        remoteDataSource.setSuccessResponse(listOf(fixtureItem1))

        val items = repository.observeItem(fixtureItem1.id).take(count = 2).toList()

        assertThat(items).containsExactly(fixtureItem1MaxedNulls, fixtureItem1).inOrder()
        assertThat(remoteDataSource.lastCalledItemId).isEqualTo(fixtureItem1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureItem1)
    }

    @Test
    fun `observe item keeps cached item when remote refresh fails`() = runTest {
        localDataSource.upsert(fixtureItem1)
        localDataSource.expiredIds.add(fixtureItem1.id)
        remoteDataSource.setErrorResponse(Unknown("Remote error"))

        val item = repository.observeItem(fixtureItem1.id).first()

        assertThat(item).isEqualTo(fixtureItem1)
        assertThat(remoteDataSource.lastCalledItemId).isEqualTo(fixtureItem1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureItem1)
    }

    @Test
    fun `observe item emits nothing when local row is absent and remote fetch fails`() = runTest {
        remoteDataSource.setErrorResponse(Unknown("Remote error"))

        val item = withTimeoutOrNull(100.milliseconds) {
            repository.observeItem(fixtureItem1.id).first()
        }

        assertThat(item).isNull()
        assertThat(remoteDataSource.lastCalledItemId).isEqualTo(fixtureItem1.id)
        assertThat(localDataSource.saved).isEmpty()
    }
}
