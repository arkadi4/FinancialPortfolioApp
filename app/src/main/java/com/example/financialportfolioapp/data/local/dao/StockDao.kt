package com.example.financialportfolioapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financialportfolioapp.data.entities.StockEntity
import com.example.financialportfolioapp.domain.entities.Stock

@Dao
interface StockDao {
    @Upsert
    suspend fun insert(stock: StockEntity)

    @Query("SELECT * FROM stocks WHERE id = :id")
    suspend fun getStockById(id: Long): Stock?

    @Query("DELETE FROM stocks WHERE id = :id")
    suspend fun deleteStockById(id: Long): Int
}
