package com.example.financialportfolioapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingsStorageRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsStorageRepository {
    private val defaultCurrencyKey = stringPreferencesKey(DEFAULT_CURRENCY_KEY)
    private val isFirstLaunch = booleanPreferencesKey(IS_FIRST_LAUNCH_KEY)

    override suspend fun getCurrencySettings(): Flow<String> {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                preferences[defaultCurrencyKey] ?: "BYN"
            }
        }
    }

    override suspend fun setCurrencySettings(newCurrency: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[defaultCurrencyKey] = newCurrency
            }
        }
    }

    override suspend fun getFirstLaunchSettings(): Flow<Boolean> {
        return withContext(Dispatchers.IO) {
            dataStore.data.map { preferences ->
                preferences[isFirstLaunch] ?: true
            }
        }
    }

    override suspend fun setFirstLaunchSettings(newValue: Boolean) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[isFirstLaunch] = newValue
            }
        }
    }

    companion object {
        const val DEFAULT_CURRENCY_KEY = "default currency"
        const val IS_FIRST_LAUNCH_KEY = "is it first launch"
    }
}
