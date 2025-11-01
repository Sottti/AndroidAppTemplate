package com.sottti.android.app.template.domain.settings.di

import com.sottti.android.app.template.domain.settings.usecase.GetSystemColorContrast
import com.sottti.android.app.template.domain.settings.usecase.GetSystemColorContrastImpl
import com.sottti.android.app.template.domain.settings.usecase.GetSystemTheme
import com.sottti.android.app.template.domain.settings.usecase.GetSystemThemeImpl
import com.sottti.android.app.template.domain.settings.usecase.ObserveDynamicColor
import com.sottti.android.app.template.domain.settings.usecase.ObserveDynamicColorImpl
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemColorContrast
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemColorContrastImpl
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemTheme
import com.sottti.android.app.template.domain.settings.usecase.ObserveSystemThemeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainSettingsModule {

    @Binds
    @Singleton
    abstract fun bindGetSystemColorContrast(
        impl: GetSystemColorContrastImpl,
    ): GetSystemColorContrast

    @Binds
    @Singleton
    abstract fun bindGetSystemTheme(
        impl: GetSystemThemeImpl,
    ): GetSystemTheme

    @Binds
    @Singleton
    abstract fun bindObserveDynamicColor(
        impl: ObserveDynamicColorImpl,
    ): ObserveDynamicColor

    @Binds
    @Singleton
    abstract fun bindObserveSystemColorContrast(
        impl: ObserveSystemColorContrastImpl,
    ): ObserveSystemColorContrast

    @Binds
    @Singleton
    abstract fun bindObserveSystemTheme(
        impl: ObserveSystemThemeImpl,
    ): ObserveSystemTheme
}
