package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface
import com.example.financialportfolioapp.domain.entities.Price

interface PortfolioItemRepository {
    suspend fun addStock(name: String, amount: Double, price: Price, dividends: Double)
    suspend fun addBond(
        name: String,
        amount: Double,
        price: Price,
        futurePrice: Price,
        yieldToMaturity: Double
    )

    suspend fun addCash(name: String, amount: Double, price: Price)
    suspend fun getItems(): List<PortfolioItemInterface>
    suspend fun getItemById(itemId: Long): PortfolioItemInterface?
    suspend fun deleteItemById(itemId: Long)
}
