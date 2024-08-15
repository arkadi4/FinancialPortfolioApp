package com.example.financialportfolioapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financialportfolioapp.data.entities.AssetEntity
import com.example.financialportfolioapp.domain.entities.Asset

@Dao
interface AssetDao {
    @Query("SELECT * FROM assets")
    suspend fun getAllAssets(): List<Asset>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(asset: AssetEntity)

    @Delete
    suspend fun deleteAsset(asset: AssetEntity)

    @Query("DELETE FROM assets WHERE id = :assetId")
    suspend fun deleteAssetById(assetId: Int)
}
