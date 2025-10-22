package com.sottti.android.app.template.data.system.features

import com.sottti.android.app.template.domain.system.features.SystemFeatures
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal fun interface SystemFeaturesDataModule {

    @Binds
    @Singleton
    fun bindSdkFeatures(
        impl: SystemFeaturesImpl,
    ): SystemFeatures
}
