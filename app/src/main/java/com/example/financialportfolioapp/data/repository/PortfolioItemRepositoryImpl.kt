package com.example.financialportfolioapp.data.repository

import com.example.financialportfolioapp.data.DataSample
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioItemRepositoryImpl @Inject constructor() : PortfolioItemRepository {
    override suspend fun getItems(): List<PortfolioItemInterface> {
        return withContext(Dispatchers.IO) { DataSample.portfolioItemsList }
    }

    override suspend fun getItemById(itemId: Int): PortfolioItemInterface? {
        return withContext(Dispatchers.IO) { getItems().firstOrNull { it.id == itemId } }
    }

    override suspend fun deleteItemById(itemId: Int) {
        withContext(Dispatchers.IO) { DataSample.deleteItem(itemId) }
    }
}
