package com.sottti.roller.android.app.template.data.settings.repository

import com.sottti.android.app.template.domain.settings.model.DynamicColor
import com.sottti.android.app.template.domain.settings.model.SystemColorContrast
import com.sottti.android.app.template.domain.settings.model.SystemTheme
import com.sottti.android.app.template.domain.settings.repository.SettingsRepository
import com.sottti.roller.android.app.template.data.settings.datasource.SettingsLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource,
) : SettingsRepository {
    override fun getSystemColorContrast(): SystemColorContrast =
        localDataSource.getSystemColorContrast()

    override fun observeDynamicColor(): Flow<DynamicColor> =
        localDataSource.observeDynamicColor()

    override fun observeSystemTheme(): Flow<SystemTheme> =
        localDataSource.observeSystemTheme()

    override fun getSystemTheme(): SystemTheme =
        localDataSource.getSystemTheme()
}
