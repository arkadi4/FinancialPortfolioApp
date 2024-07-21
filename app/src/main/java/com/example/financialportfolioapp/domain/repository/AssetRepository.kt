package com.example.financialportfolioapp.domain.repository

import com.example.financialportfolioapp.domain.entities.Asset

interface AssetRepository {
    fun getAssets(): List<Asset>
    fun getAssetById(assetId: Int): Asset
}
