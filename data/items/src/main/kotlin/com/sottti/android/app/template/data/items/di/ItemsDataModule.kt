package com.sottti.android.app.template.data.items.di

import com.sottti.android.app.template.data.items.repository.ItemsRepositoryImpl
import com.sottti.android.app.template.repository.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface ItemsDataModule {

    @Binds
    @Singleton
    fun bindRepository(
        impl: ItemsRepositoryImpl,
    ): ItemsRepository
}
