package com.sottti.android.app.template.data.items.di

import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSource
import com.sottti.android.app.template.data.items.datasource.local.ItemsLocalDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSource
import com.sottti.android.app.template.data.items.datasource.remote.ItemsRemoteDataSourceImpl
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCalls
import com.sottti.android.app.template.data.items.datasource.remote.api.ItemsApiCallsImpl
import com.sottti.android.app.template.data.items.repository.ItemsRepositoryImpl
import com.sottti.android.app.template.repository.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ItemsDataModule {

    @Binds
    @Singleton
    fun bindLocalDataSource(
        impl: ItemsLocalDataSourceImpl,
    ): ItemsLocalDataSource

    @Binds
    @Singleton
    fun bindRemoteDataSource(
        impl: ItemsRemoteDataSourceImpl,
    ): ItemsRemoteDataSource

    @Binds
    @Singleton
    fun bindRepository(
        impl: ItemsRepositoryImpl,
    ): ItemsRepository

    @Binds
    @Singleton
    fun bindApiCalls(
        impl: ItemsApiCallsImpl,
    ): ItemsApiCalls
}
