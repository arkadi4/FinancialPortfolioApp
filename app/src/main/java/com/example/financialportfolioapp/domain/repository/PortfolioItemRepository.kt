package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface

interface PortfolioItemRepository {
    suspend fun getItems(): List<PortfolioItemInterface>
    suspend fun getItemById(itemId: Int): PortfolioItemInterface?
    suspend fun deleteItemById(itemId: Int)
}
