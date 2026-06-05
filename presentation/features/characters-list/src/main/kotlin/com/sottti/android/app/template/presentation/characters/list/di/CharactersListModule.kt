package com.sottti.android.app.template.presentation.characters.list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(ViewModelComponent::class)
internal object CharactersListModule {

    @Provides
    @Suppress("FunctionOnlyReturningConstant")
    fun provideCoroutineScope(): CoroutineScope? = null
}
