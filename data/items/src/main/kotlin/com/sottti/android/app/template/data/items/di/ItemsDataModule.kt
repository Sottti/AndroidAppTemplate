package com.sottti.android.app.template.data.items.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDatabase
import com.sottti.android.app.template.data.items.datasource.local.database.RemoteKeysDao
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCallsImpl
import com.sottti.android.app.template.data.items.mediator.ItemsRemoteMediator
import com.sottti.android.app.template.data.items.repository.ItemsRepositoryImpl
import com.sottti.android.app.template.model.Item
import com.sottti.android.app.template.repository.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ItemsDataModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: ItemsLocalDataSourceImpl,
    ): ItemsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: ItemsRemoteDataSourceImpl,
    ): ItemsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: ItemsRepositoryImpl,
    ): ItemsRepository

    @Binds
    @Singleton
    abstract fun bindApiCalls(
        impl: ItemsApiCallsImpl,
    ): ItemsApiCalls

    @Binds
    @Singleton
    @OptIn(ExperimentalPagingApi::class)
    abstract fun bindItemsRemoteMediator(
        impl: ItemsRemoteMediator,
    ): RemoteMediator<Int, Item>

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
        ): ItemsDatabase = ItemsDatabase.create(context)

        @Provides
        @Singleton
        fun provideItemsDao(
            database: ItemsDatabase,
        ): ItemsDao = database.itemsDao

        @Provides
        @Singleton
        fun provideRemoteKeysDao(
            database: ItemsDatabase,
        ): RemoteKeysDao = database.remoteKeysDao
    }
}
