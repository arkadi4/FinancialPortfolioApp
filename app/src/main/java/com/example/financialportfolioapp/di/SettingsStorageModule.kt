package com.example.financialportfolioapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.financialportfolioapp.data.repository.SettingsStorageRepositoryImpl
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsStorageModule {
    @Singleton
    @Provides
    fun provideSettingsStorageRepository(
        @ApplicationContext context: Context
    ): SettingsStorageRepository = SettingsStorageRepositoryImpl(provideDataStore(context))

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("Settings storage") }
        )
    }

//    @Singleton
//    @Binds
//    fun bindSS () {}

//    @Binds
//    fun bindSettingsStorageRepository(
//        settingsStorageRepositoryImpl: SettingsStorageRepositoryImpl
//    ): SettingsStorageRepository
}
