package com.example.financialportfolioapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.financialportfolioapp.data.entities.UniqueIdEntity

@Dao
interface UniqueIdDao {
    @Insert
    suspend fun insert(uniqueId: UniqueIdEntity): Long

    @Transaction
    suspend fun generateNewId(): Long {
        val uniqueId = UniqueIdEntity()
        return insert(uniqueId)
    }
}
