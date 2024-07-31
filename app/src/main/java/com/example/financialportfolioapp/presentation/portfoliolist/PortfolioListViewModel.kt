package com.example.financialportfolioapp.presentation.portfoliolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioListViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _items = MutableLiveData<List<PortfolioItem>>()
    val items: LiveData<List<PortfolioItem>> get() = _items

    init {
        _items.value = loadSampleData()
    }

    private fun loadSampleData(): List<PortfolioItem> {
        return portfolioItemRepository.getItems()
    }
}
