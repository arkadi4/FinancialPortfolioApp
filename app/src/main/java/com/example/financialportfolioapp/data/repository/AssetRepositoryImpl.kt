package com.example.financialportfolioapp.data.repository

import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.domain.entities.Asset
import com.example.financialportfolioapp.domain.repository.AssetRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class AssetRepositoryImpl @Inject constructor(
    private val assetDao: AssetDao
) : AssetRepository {
    override suspend fun getAssets(): List<Asset> {
        return withContext(Dispatchers.IO) { assetDao.getAllAssets() }
    }

    override suspend fun getAssetById(assetId: Long): Asset? {
        return withContext(Dispatchers.IO) { getAssets().firstOrNull { it.id.toLong() == assetId } }
    }

    override suspend fun deleteAssetById(assetId: Long) {
        withContext(Dispatchers.IO) { assetDao.deleteAssetById(assetId) }
    }
}
