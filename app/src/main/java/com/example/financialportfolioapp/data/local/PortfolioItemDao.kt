package com.example.financialportfolioapp.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PortfolioItemDao {
    @Query("Select id FROM stocks WHERE portfolioItemId = :portfolioItemId")
    suspend fun getStockIdByPortfolioItemId(portfolioItemId: Int): Int?

    @Query("SELECT id FROM bonds WHERE portfolioItemId = :portfolioItemId")
    suspend fun getBondIdByPortfolioItemId(portfolioItemId: Int): Int?
}
