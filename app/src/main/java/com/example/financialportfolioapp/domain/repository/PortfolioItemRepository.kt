package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.Cash
import com.example.financialportfolioapp.domain.entities.PortfolioItem
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

    suspend fun addCash(name: String, amount: Double, price: Price, exchangeRatioToUSD: Double)
    suspend fun getItems(): List<PortfolioItem>
    suspend fun getItemById(itemId: Long): PortfolioItem?
    suspend fun deleteItemById(itemId: Long)
    suspend fun addSamples()
    suspend fun getAllCashObjects(): List<Cash>
    suspend fun deleteAll()
    suspend fun getPortfolioItemById(itemId: Long): PortfolioItem?
}
