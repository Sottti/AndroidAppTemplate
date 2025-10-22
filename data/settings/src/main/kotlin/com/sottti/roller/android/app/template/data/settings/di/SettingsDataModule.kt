package com.sottti.roller.android.app.template.data.settings.di

import android.app.UiModeManager
import android.content.Context
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import com.sottti.roller.android.app.template.data.settings.repository.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface SettingsDataModule {

    @Binds
    @Singleton
    fun bindRepository(
        impl: SettingsRepositoryImpl,
    ): SettingsRepository

    companion object {
        @Provides
        @Singleton
        fun provideUiModeManager(
            @ApplicationContext context: Context,
        ): UiModeManager? = context.getSystemService(UiModeManager::class.java)
    }
}
