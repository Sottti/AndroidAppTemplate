package com.sottti.android.app.template.domain.items.di

import com.sottti.android.app.template.domain.items.usecase.ObserveItem
import com.sottti.android.app.template.domain.items.usecase.ObserveItemImpl
import com.sottti.android.app.template.domain.items.usecase.ObserveItems
import com.sottti.android.app.template.domain.items.usecase.ObserveItemsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainItemsModule {

    @Binds
    @Singleton
    abstract fun bindObserveItem(
        impl: ObserveItemImpl,
    ): ObserveItem

    @Binds
    @Singleton
    abstract fun bindObserveItems(
        impl: ObserveItemsImpl,
    ): ObserveItems
}
