package com.example.financialportfolioapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsStorageRepository {
    suspend fun getCurrencySettings(): Flow<String>
    suspend fun setCurrencySettings(newCurrency: String)
    suspend fun getFirstLaunchSettings(): Flow<Boolean>
    suspend fun setFirstLaunchSettings(newValue: Boolean)
}
