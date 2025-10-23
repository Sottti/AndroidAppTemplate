package com.sottti.android.app.template.data.settings.repository

import com.sottti.android.app.template.data.settings.datasource.SettingsLocalDataSource
import com.sottti.android.app.template.domain.cores.models.DynamicColor
import com.sottti.android.app.template.domain.cores.models.SystemColorContrast
import com.sottti.android.app.template.domain.cores.models.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {
    override fun getSystemColorContrast(): SystemColorContrast =
        localDataSource.getSystemColorContrast()

    override fun observeSystemColorContrast(): Flow<SystemColorContrast> =
        localDataSource.observeSystemColorContrast()

    override fun observeDynamicColor(): Flow<DynamicColor> =
        localDataSource.observeDynamicColor()

    override fun observeSystemTheme(): Flow<SystemTheme> =
        localDataSource.observeSystemTheme()

    override fun getSystemTheme(): SystemTheme =
        localDataSource.getSystemTheme()
}
