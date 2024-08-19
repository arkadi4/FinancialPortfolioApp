package com.example.financialportfolioapp.presentation.createBond

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CreateBondViewModel @Inject constructor(
    private val repository: PortfolioItemRepository
):ViewModel() {
    fun addBond(
        name: String,
        amount: Double,
        price: Price,
        futurePrice: Price,
        yieldToMaturity: Double
    ) {
        viewModelScope.launch {
            repository.addBond(name, amount, price, futurePrice, yieldToMaturity)
        }
    }
}


