package com.sottti.android.app.template.data.items.di

import android.content.Context
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.local.SystemTimeProvider
import com.sottti.android.app.template.data.items.datasource.local.TimeProvider
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDao
import com.sottti.android.app.template.data.items.datasource.local.database.ItemsDatabase
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCallsImpl
import com.sottti.android.app.template.data.items.repository.ItemsRepositoryImpl
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
    abstract fun bindTimeProvider(
        impl: SystemTimeProvider,
    ): TimeProvider

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
        ): ItemsDatabase = ItemsDatabase.create(context)

        @Provides
        fun provideItemsDao(
            database: ItemsDatabase,
        ): ItemsDao = database.itemsDao()
    }
}
