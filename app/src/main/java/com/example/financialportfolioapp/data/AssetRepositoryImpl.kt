package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.repository.AssetRepository
import javax.inject.Inject

class AssetRepositoryImpl @Inject constructor() : AssetRepository {
    override fun getAssets(): List<Asset> {
        return DataSample.assetList
    }

    override fun getAssetById(assetId: Int): Asset? {
        return getAssets().firstOrNull { it.id == assetId }
    }

    override fun deleteAssetById(assetId: Int) {
        DataSample.deleteAsset(assetId)
    }
}
