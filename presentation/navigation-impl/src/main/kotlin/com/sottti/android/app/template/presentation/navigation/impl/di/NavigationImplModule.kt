package com.sottti.android.app.template.presentation.navigation.impl.di

import com.sottti.android.app.template.presentation.navigation.impl.manager.NavigationManagerImpl
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationImplModule {

    @Binds
    @Singleton
    fun bindNavigationManager(
        impl: NavigationManagerImpl,
    ): NavigationManager
}
