package com.example.financialportfolioapp.domain.interactor

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssetListInteractor @Inject constructor(
    private val assetRepository: AssetRepository,
    private val portfolioItemRepository: PortfolioItemRepository
) {
    suspend fun getAssets(): List<Asset> {
        return assetRepository.getAssets()
    }

    suspend fun deleteAssetById(id: Int) {
        assetRepository.deleteAssetById(id)
    }

    suspend fun deleteItemById(id: Int) {
        portfolioItemRepository.deleteItemById(id)
    }
}
