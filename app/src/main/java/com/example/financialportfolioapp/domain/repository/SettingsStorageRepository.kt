package com.example.financialportfolioapp.domain.repository

interface SettingsStorageRepository {
    fun getSettings(): String
    fun setSettings(newCurrency: String)
}
