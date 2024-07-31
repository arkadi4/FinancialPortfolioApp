package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.PortfolioItem
import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface

interface PortfolioItemRepository {
    fun getItems(): List<PortfolioItem>
    fun getItemById(assetId: Int): PortfolioItemInterface?
}
