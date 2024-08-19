package com.example.financialportfolioapp.presentation.createCash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.entities.Price
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCashViewModel @Inject constructor(
    private val repository: PortfolioItemRepository
): ViewModel() {
   fun  addCash(name: String, amount:Double, price: Price){
       viewModelScope.launch {
           repository.addCash(name, amount, price)
       }
   }
}