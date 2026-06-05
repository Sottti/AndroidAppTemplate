package com.sottti.android.app.template.di.domain.characters

import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacter
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacterImpl
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharacters
import com.sottti.android.app.template.domain.characters.usecase.ObserveCharactersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainCharactersModule {

    @Binds
    @Singleton
    abstract fun bindObserveCharacter(
        impl: ObserveCharacterImpl,
    ): ObserveCharacter

    @Binds
    @Singleton
    abstract fun bindObserveCharacters(
        impl: ObserveCharactersImpl,
    ): ObserveCharacters
}
