package com.sottti.android.app.template.data.characters.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.sottti.android.app.template.data.characters.datasource.local.CharactersLocalDataSource
import com.sottti.android.app.template.data.characters.datasource.local.CharactersRoomDataSource
import com.sottti.android.app.template.data.characters.datasource.local.SystemTimeProvider
import com.sottti.android.app.template.data.characters.datasource.local.TimeProvider
import com.sottti.android.app.template.data.characters.datasource.local.database.CharactersDao
import com.sottti.android.app.template.data.characters.datasource.local.database.CharactersDatabase
import com.sottti.android.app.template.data.characters.datasource.local.database.RemoteKeysDao
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersApiDataSource
import com.sottti.android.app.template.data.characters.datasource.remote.CharactersRemoteDataSource
import com.sottti.android.app.template.data.characters.datasource.remote.api.CharactersApiCalls
import com.sottti.android.app.template.data.characters.datasource.remote.api.CharactersApiCallsImpl
import com.sottti.android.app.template.data.characters.mediator.CharactersRemoteMediator
import com.sottti.android.app.template.data.characters.repository.CharactersRepositoryImpl
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CharactersDataModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: CharactersRoomDataSource,
    ): CharactersLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: CharactersApiDataSource,
    ): CharactersRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: CharactersRepositoryImpl,
    ): CharactersRepository

    @Binds
    @Singleton
    abstract fun bindApiCalls(
        impl: CharactersApiCallsImpl,
    ): CharactersApiCalls

    @Binds
    @Singleton
    abstract fun bindTimeProvider(
        impl: SystemTimeProvider,
    ): TimeProvider

    @Binds
    @Singleton
    @OptIn(ExperimentalPagingApi::class)
    abstract fun bindCharactersRemoteMediator(
        impl: CharactersRemoteMediator,
    ): RemoteMediator<Int, Character>

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
        ): CharactersDatabase = CharactersDatabase.create(context)

        @Provides
        @Singleton
        fun provideCharactersDao(
            database: CharactersDatabase,
        ): CharactersDao = database.charactersDao

        @Provides
        @Singleton
        fun provideRemoteKeysDao(
            database: CharactersDatabase,
        ): RemoteKeysDao = database.remoteKeysDao
    }
}
