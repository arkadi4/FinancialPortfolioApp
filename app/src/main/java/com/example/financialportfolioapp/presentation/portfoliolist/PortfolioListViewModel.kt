package com.example.financialportfolioapp.presentation.portfoliolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financialportfolioapp.domain.entities.Bond
import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Stock
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import com.example.financialportfolioapp.presentation.entitiespresentation.BondUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.CashUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.PortfolioItemUiModel
import com.example.financialportfolioapp.presentation.entitiespresentation.StockUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioListViewModel @Inject constructor(
    private val portfolioItemRepository: PortfolioItemRepository
) : ViewModel() {
    private val _items = MutableLiveData<List<PortfolioItemUiModel>>()
    val items: LiveData<List<PortfolioItemUiModel>> get() = _items

    init {
        _items.value = mapDataToUiModel(loadSampleData())
    }

    private fun loadSampleData(): List<PortfolioItemInterface> {
        return portfolioItemRepository.getItems()
    }

    private fun mapDataToUiModel(data: List<PortfolioItemInterface>): List<PortfolioItemUiModel> {
        return data.map {
            when (it) {
                is Cash -> CashUiModel(it)
                is Stock -> StockUiModel(it)
                is Bond -> BondUiModel(it)
                else -> throw RuntimeException("Illegal view type")
            }
        }
    }
}
