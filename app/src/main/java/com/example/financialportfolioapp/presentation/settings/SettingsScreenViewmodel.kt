package com.example.financialportfolioapp.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SettingsState(
    val defaultCurrencyValue: String
)

@HiltViewModel
class SettingsScreenViewmodel @Inject constructor(
    val settingsStorageRepository: SettingsStorageRepository
) : ViewModel() {
    private val _uiState = MutableLiveData<SettingsState>()
    val uiState: LiveData<SettingsState> get() = _uiState

    init {
        _uiState.value = SettingsState(settingsStorageRepository.getSettings())
    }

    fun setDefaultCurrency(newCurrency: String) {
        _uiState.postValue(_uiState.value!!.copy(defaultCurrencyValue = newCurrency))
        settingsStorageRepository.setSettings(newCurrency)
    }
}
