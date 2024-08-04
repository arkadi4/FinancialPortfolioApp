package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.Asset

interface AssetRepository {
    suspend fun getAssets(): List<Asset>
    suspend fun getAssetById(assetId: Int): Asset?
    suspend fun deleteAssetById(assetId: Int)
}
