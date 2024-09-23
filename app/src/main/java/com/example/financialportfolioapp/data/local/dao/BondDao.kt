package com.example.financialportfolioapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.financialportfolioapp.data.entities.BondEntity
import com.example.financialportfolioapp.domain.entities.Bond

@Dao
interface BondDao {
    @Upsert
    suspend fun insert(stock: BondEntity)

    @Query("SELECT * FROM bonds WHERE id = :id")
    suspend fun getBondById(id: Long): Bond?

    @Query("SELECT * FROM bonds")
    suspend fun getAllBonds(): List<Bond>

    @Query("DELETE FROM bonds WHERE id = :id")
    suspend fun deleteBondById(id: Long): Int

    @Query("DELETE FROM bonds")
    suspend fun deleteAllBonds()
}
