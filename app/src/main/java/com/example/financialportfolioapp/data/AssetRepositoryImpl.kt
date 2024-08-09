package com.example.financialportfolioapp.data

import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetRepositoryImpl @Inject constructor() : AssetRepository {
    override suspend fun getAssets(): List<Asset> {
        return withContext(Dispatchers.IO) { DataSample.assetList }
    }

    override suspend fun getAssetById(assetId: Int): Asset? {
        return withContext(Dispatchers.IO) { getAssets().firstOrNull { it.id == assetId } }
    }

    override suspend fun deleteAssetById(assetId: Int) {
        withContext(Dispatchers.IO) { DataSample.deleteAsset(assetId) }
    }
}
