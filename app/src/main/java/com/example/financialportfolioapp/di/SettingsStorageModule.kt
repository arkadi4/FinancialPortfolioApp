package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.data.repository.SettingsStorageRepositoryImpl
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingsStorageModule {
    @Binds
    @Singleton
    fun bindSettingsStorageRepository(
        settingsStorageRepositoryImpl: SettingsStorageRepositoryImpl
    ): SettingsStorageRepository
}
