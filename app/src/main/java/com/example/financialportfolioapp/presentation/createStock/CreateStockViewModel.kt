package com.example.financialportfolioapp.presentation.createStock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateStockViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    fun addStock(name: String, amount: Double, price: Price, dividends: Double) {
        viewModelScope.launch {
            portfolioItemRepository.addStock(name, amount, price, dividends)
        }


    }
}
