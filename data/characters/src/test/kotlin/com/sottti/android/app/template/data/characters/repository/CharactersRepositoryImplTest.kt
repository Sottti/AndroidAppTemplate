package com.sottti.android.app.template.data.characters.repository

import com.google.common.truth.Truth.assertThat
import com.sottti.android.app.template.data.characters.datasource.local.CharactersLocalDataSourceFake
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersRemoteDataSourceFake
import com.sottti.android.app.template.data.characters.mediator.CharactersRemoteMediator
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1
import com.sottti.android.app.template.domain.characters.fixtures.fixtureCharacter1MaxedNulls
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

internal class CharactersRepositoryImplTest {

    private lateinit var localDataSource: CharactersLocalDataSourceFake
    private lateinit var remoteDataSource: CharactersRemoteDataSourceFake
    private lateinit var repository: CharactersRepositoryImpl

    @Before
    fun setUp() {
        localDataSource = CharactersLocalDataSourceFake()
        remoteDataSource = CharactersRemoteDataSourceFake()
        repository = CharactersRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            remoteMediator = CharactersRemoteMediator(
                localDataSource = localDataSource,
                remoteDataSource = remoteDataSource,
            ),
        )
    }

    @Test
    fun `observe character fetches remote character when local row is absent`() = runTest {
        remoteDataSource.setSuccessResponse(listOf(fixtureCharacter1))

        val character = repository.observeCharacter(fixtureCharacter1.id).first()

        assertThat(character).isEqualTo(fixtureCharacter1)
        assertThat(remoteDataSource.lastCalledCharacterId).isEqualTo(fixtureCharacter1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureCharacter1)
    }

    @Test
    fun `observe character refreshes expired cached character`() = runTest {
        localDataSource.upsert(fixtureCharacter1MaxedNulls)
        localDataSource.expiredIds.add(fixtureCharacter1.id)
        remoteDataSource.setSuccessResponse(listOf(fixtureCharacter1))

        val characters = repository.observeCharacter(fixtureCharacter1.id).take(count = 2).toList()

        assertThat(characters).containsExactly(fixtureCharacter1MaxedNulls, fixtureCharacter1).inOrder()
        assertThat(remoteDataSource.lastCalledCharacterId).isEqualTo(fixtureCharacter1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureCharacter1)
    }

    @Test
    fun `observe character keeps cached character when remote refresh fails`() = runTest {
        localDataSource.upsert(fixtureCharacter1)
        localDataSource.expiredIds.add(fixtureCharacter1.id)
        remoteDataSource.setErrorResponse(Unknown("Remote error"))

        val character = repository.observeCharacter(fixtureCharacter1.id).first()

        assertThat(character).isEqualTo(fixtureCharacter1)
        assertThat(remoteDataSource.lastCalledCharacterId).isEqualTo(fixtureCharacter1.id)
        assertThat(localDataSource.saved).containsExactly(fixtureCharacter1)
    }

    @Test
    fun `observe character emits nothing when local row is absent and remote fetch fails`() = runTest {
        remoteDataSource.setErrorResponse(Unknown("Remote error"))

        val character = withTimeoutOrNull(100.milliseconds) {
            repository.observeCharacter(fixtureCharacter1.id).first()
        }

        assertThat(character).isNull()
        assertThat(remoteDataSource.lastCalledCharacterId).isEqualTo(fixtureCharacter1.id)
        assertThat(localDataSource.saved).isEmpty()
    }
}
