package com.example.financialportfolioapp.domain.interactor

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import javax.inject.Singleton

@Singleton
class Interactor(
    private val assetRepository: AssetRepository,
    private val portfolioItemRepository: PortfolioItemRepository
) {
    fun getAssets(): List<Asset> {
        return assetRepository.getAssets()
    }

    fun deleteAssetById(id: Int) {
        assetRepository.deleteAssetById(id)
    }

    fun deleteItemById(id: Int) {
        portfolioItemRepository.deleteItemById(id)
    }
}
