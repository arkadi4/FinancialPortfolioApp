package com.example.financialportfolioapp.presentation.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

data class SettingsState(
    val defaultCurrencyValue: String
)

class SettingsScreenViewmodel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableLiveData<SettingsState>()
    val uiState: MutableLiveData<SettingsState> get() = _uiState
    private val sharedPrefs: SharedPreferences =
        application.getSharedPreferences("preference_key", Context.MODE_PRIVATE)
    private val defaultCurrencyInSharedPrefs: String = sharedPrefs
        .getString("default Currency", "BYN")
        ?: "BYN"

    init {
        _uiState.value = SettingsState(defaultCurrencyInSharedPrefs)
    }

    fun setDefaultCurrency(newCurrency: String) {
        _uiState.postValue(_uiState.value!!.copy(defaultCurrencyValue = newCurrency))
        sharedPrefs.edit()?.putString("default Currency", newCurrency)?.apply()
    }
}
