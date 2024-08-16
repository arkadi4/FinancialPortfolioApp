package com.example.financialportfolioapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingsStorageRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsStorageRepository {
    private val defaultCurrencyKey = stringPreferencesKey(DEFAULT_CURRENCY_KEY)

    override suspend fun getSettings(): String {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { prefs ->
                prefs[defaultCurrencyKey] ?: "BYN"
            }.first()
        }
    }

    override suspend fun setSettings(newCurrency: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[defaultCurrencyKey] = newCurrency
            }
        }
    }

    companion object {
        const val DEFAULT_CURRENCY_KEY = "default currency"
    }
}
