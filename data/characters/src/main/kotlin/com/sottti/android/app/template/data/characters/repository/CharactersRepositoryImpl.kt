package com.sottti.android.app.template.data.characters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.michaelbull.result.onOk
import com.sottti.android.app.template.data.characters.datasource.local.CharactersLocalDataSource
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersRemoteDataSource
import com.sottti.android.app.template.data.characters.mediator.CharactersRemoteMediator
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.model.CharacterId
import com.sottti.android.app.template.domain.characters.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class CharactersRepositoryImpl @Inject constructor(
    private val localDataSource: CharactersLocalDataSource,
    private val remoteDataSource: CharactersRemoteDataSource,
    private val remoteMediator: CharactersRemoteMediator,
) : CharactersRepository {

    override fun observeCharacter(characterId: CharacterId): Flow<Character> =
        localDataSource
            .observeCharacter(characterId)
            .onEach { character ->
                if (character == null || localDataSource.isExpired(character.id)) {
                    remoteDataSource
                        .getCharacter(characterId)
                        .onOk { localDataSource.upsert(it) }
                }
            }.filterNotNull()

    @OptIn(ExperimentalPagingApi::class)
    override fun observeCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = CharactersRemoteDataSource.PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = CharactersRemoteDataSource.PAGE_SIZE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = { localDataSource.observeCharacters() },
            remoteMediator = remoteMediator,
        ).flow
    }
}
