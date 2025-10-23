package com.sottti.android.app.template.data.settings.di

import android.content.Context
import com.sottti.android.app.template.data.settings.managers.SystemColorContrastManager
import com.sottti.android.app.template.data.settings.managers.SystemColorContrastManagerImpl
import com.sottti.android.app.template.data.settings.managers.ThemeManager
import com.sottti.android.app.template.data.settings.managers.ThemeManagerImpl
import com.sottti.android.app.template.data.settings.managers.UiModeManager
import com.sottti.android.app.template.data.settings.managers.UiModeManagerImpl
import com.sottti.android.app.template.data.settings.model.AndroidUiModeManager
import com.sottti.android.app.template.data.settings.repository.SettingsRepositoryImpl
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsDataModule {

    @Binds
    @Singleton
    fun bindRepository(
        impl: SettingsRepositoryImpl,
    ): SettingsRepository

    @Binds
    @Singleton
    fun bindThemeManager(
        impl: ThemeManagerImpl,
    ): ThemeManager

    @Binds
    @Singleton
    fun bindSystemColorContrastManager(
        impl: SystemColorContrastManagerImpl,
    ): SystemColorContrastManager

    @Binds
    @Singleton
    fun bindUiModeManager(
        impl: UiModeManagerImpl,
    ): UiModeManager

    companion object {
        @Provides
        @Singleton
        fun provideUiModeManager(
            @ApplicationContext context: Context,
        ): AndroidUiModeManager? =
            context.getSystemService(AndroidUiModeManager::class.java)
    }
}
