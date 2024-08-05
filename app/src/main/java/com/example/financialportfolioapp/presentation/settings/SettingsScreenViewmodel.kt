package com.example.financialportfolioapp.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsState(
    val defaultCurrencyValue: String
)

@HiltViewModel
class SettingsScreenViewmodel @Inject constructor(
    private val settingsStorageRepository: SettingsStorageRepository
) : ViewModel() {
    private val _uiState = MutableLiveData<SettingsState>()
    val uiState: LiveData<SettingsState> get() = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = SettingsState(settingsStorageRepository.getSettings())
        }
    }

    suspend fun setDefaultCurrency(newCurrency: String) {
        _uiState.value = _uiState.value!!.copy(defaultCurrencyValue = newCurrency)
        settingsStorageRepository.setSettings(newCurrency)
    }
}
