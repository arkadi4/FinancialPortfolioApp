package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import javax.inject.Inject

class PortfolioItemRepositoryImpl @Inject constructor() : PortfolioItemRepository {
    override fun getItems(): List<PortfolioItemInterface> {
        return DataSample.portfolioItemsList
    }

    override fun getItemById(itemId: Int): PortfolioItemInterface? {
        return getItems().firstOrNull { it.id == itemId }
    }

    override fun deleteItemById(itemId: Int) {
        DataSample.deleteItem(itemId)
    }
}
