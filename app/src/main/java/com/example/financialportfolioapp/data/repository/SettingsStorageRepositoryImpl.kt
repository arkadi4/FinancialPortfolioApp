package com.example.financialportfolioapp.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsStorageRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : SettingsStorageRepository {
    private val settingsStorage: SharedPreferences =
        context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE)
    override suspend fun getSettings(): String {
        return withContext(Dispatchers.IO) {
            settingsStorage.getString(DEFAULT_CURRENCY_KEY, "BYN") ?: "BYN"
        }
    }

    override suspend fun setSettings(newCurrency: String) {
        withContext(Dispatchers.IO) {
            settingsStorage.edit().putString(DEFAULT_CURRENCY_KEY, newCurrency).apply()
        }
    }

    companion object {
        const val SETTINGS_STORAGE_NAME = "Default currency storage"
        const val DEFAULT_CURRENCY_KEY = "default currency"
    }
}
