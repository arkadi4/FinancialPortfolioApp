package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.PortfolioItem

interface PortfolioItemRepository {
    fun getItems(): List<PortfolioItem>
    fun getItemById(itemId: Int): PortfolioItem?
    fun deleteItemById(itemId: Int)
}
