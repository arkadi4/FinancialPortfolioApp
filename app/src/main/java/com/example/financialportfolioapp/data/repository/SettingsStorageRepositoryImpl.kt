package com.example.financialportfolioapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingsStorageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsStorageRepository {
    private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = SETTINGS_STORAGE_NAME)
    private val defaultCurrencyKey = stringPreferencesKey(DEFAULT_CURRENCY_KEY)

    override suspend fun getSettings(): String {
        return withContext(Dispatchers.IO) {
            context.dataStore.data.map { prefs ->
                prefs[defaultCurrencyKey] ?: "BYN"
            }.first()
        }
    }

    override suspend fun setSettings(newCurrency: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[defaultCurrencyKey] = newCurrency
            }
        }
    }

    companion object {
        const val SETTINGS_STORAGE_NAME = "Settings storage"
        const val DEFAULT_CURRENCY_KEY = "default currency"
    }
}
