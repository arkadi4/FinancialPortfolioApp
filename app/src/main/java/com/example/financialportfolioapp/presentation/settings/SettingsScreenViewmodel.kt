package com.example.financialportfolioapp.presentation.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

data class SettingsState(
    val defaultCurrencyValue: String = "default"
)

@HiltViewModel
class SettingsScreenViewmodel @Inject constructor(
    private val settingsStorageRepository: SettingsStorageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsStorageRepository.getCurrencySettings().collect {
                _uiState.value = SettingsState(it)
            }
        }
    }

    fun setDefaultCurrency(newCurrency: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(defaultCurrencyValue = newCurrency)
            settingsStorageRepository.setCurrencySettings(newCurrency)
        }
    }
}
