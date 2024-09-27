package com.example.financialportfolioapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financialportfolioapp.data.entities.CashEntity
import com.example.financialportfolioapp.domain.entities.Cash

@Dao
interface CashDao {
    @Upsert
    suspend fun insert(stock: CashEntity)

    @Query("SELECT * FROM cash WHERE id = :id")
    suspend fun getCashById(id: Long): Cash?

    @Query("DELETE FROM cash WHERE id = :id")
    suspend fun deleteCashById(id: Long): Int

    @Query("SELECT * FROM cash")
    suspend fun getAllCash(): List<Cash>

    @Query("Delete From cash")
    suspend fun deleteAllCash()
}
