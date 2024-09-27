package com.example.financialportfolioapp.presentation.portfoliodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PortfolioDetailsViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _item = MutableStateFlow<PortfolioItem?>(null)
    val item = _item.asStateFlow()

    fun loadItem(itemId: Long) {
        viewModelScope.launch {
            _item.value = portfolioItemRepository.getItemById(itemId)
        }
    }
}
