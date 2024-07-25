package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.PortfolioItemInterface

interface PortfolioItemRepository {
    fun getItems(): List<PortfolioItemInterface>
    fun getItemById(assetId: Int): PortfolioItemInterface?
}
