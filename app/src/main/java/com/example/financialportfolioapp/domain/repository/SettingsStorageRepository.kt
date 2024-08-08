package com.example.financialportfolioapp.domain.repository

interface SettingsStorageRepository {
    suspend fun getSettings(): String
    suspend fun setSettings(newCurrency: String)
}
