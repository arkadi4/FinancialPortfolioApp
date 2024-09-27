package com.example.financialportfolioapp.presentation.portfoliolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.data.DataSample
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import com.example.financialportfolioapp.domain.repository.SettingsStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class PortfolioListUiState(
    val portfolioList: List<PortfolioItem>,
    val isAlertDialogOnScreen: Boolean = false,
    val isDropDownMenuExpanded: Boolean = false,
    val selectedType: String = ""
)

@HiltViewModel
class PortfolioListViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository,
    private val settingsStorageRepository: SettingsStorageRepository
) : ViewModel() {
    private val _portfolioListUiState = MutableStateFlow(PortfolioListUiState(emptyList()))
    val portfolioListUiState = _portfolioListUiState.asStateFlow()

    init {
        checkFirstLaunch()
        viewModelScope.launch {
            _portfolioListUiState.value = _portfolioListUiState.value.copy(
                portfolioList = portfolioItemRepository.getItems()
            )
        }
    }

    fun selectTypeFromDropDownMenu(newType: String) {
        _portfolioListUiState.value = _portfolioListUiState.value.copy(
            selectedType = newType
        )
    }

    fun toggleExpanded() {
        _portfolioListUiState.value = _portfolioListUiState.value.copy(
            isDropDownMenuExpanded = !_portfolioListUiState.value.isDropDownMenuExpanded
        )
    }

    fun hideDropDownMenu() {
        _portfolioListUiState.value = _portfolioListUiState.value.copy(
            isDropDownMenuExpanded = false
        )
    }

    fun addSamplesWithClick() {
        viewModelScope.launch {
            loadSampleData()
            _portfolioListUiState.value = _portfolioListUiState.value.copy(
                portfolioList = portfolioItemRepository.getItems()
            )
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            portfolioItemRepository.deleteAll()
            _portfolioListUiState.value = _portfolioListUiState.value.copy(
                portfolioList = portfolioItemRepository.getItems()
            )
        }
    }

    private fun checkFirstLaunch() {
        viewModelScope.launch {
            if (settingsStorageRepository.getFirstLaunchSettings().first()) {
                loadSampleData()
                settingsStorageRepository.setFirstLaunchSettings(false)
            }
        }
    }

    private fun loadSampleData() {
        viewModelScope.launch {
            DataSample.portfolioItemsList.forEach { item ->
                when (item) {
                    is Cash -> portfolioItemRepository.addCash(
                        name = item.name,
                        amount = item.amount,
                        price = item.price,
                        exchangeRatioToUSD = item.exchangeRatioToUSD
                    )
                    is Stock -> portfolioItemRepository.addStock(
                        name = item.name,
                        amount = item.amount,
                        price = item.price,
                        dividends = item.dividends
                    )
                    is Bond -> portfolioItemRepository.addBond(
                        name = item.name,
                        amount = item.amount,
                        price = item.price,
                        futurePrice = item.futurePrice,
                        yieldToMaturity = item.yieldToMaturity
                    )
                }
            }
            _portfolioListUiState.value = _portfolioListUiState.value.copy(
                portfolioList = portfolioItemRepository.getItems()
            )
        }
    }

    fun showAlertDialog() {
        _portfolioListUiState.value = _portfolioListUiState.value.copy(
            isAlertDialogOnScreen = true
        )
    }

    fun hideAlertDialog() {
        _portfolioListUiState.value = _portfolioListUiState.value.copy(
            isAlertDialogOnScreen = false
        )
    }
}
