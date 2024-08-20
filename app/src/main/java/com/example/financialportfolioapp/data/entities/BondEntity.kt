package com.example.financialportfolioapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financialportfolioapp.domain.entities.Price

@Entity(
    tableName = "bonds"
)
data class BondEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "price")
    val price: Price,
    @ColumnInfo(name = "futurePrice")
    val futurePrice: Price,
    @ColumnInfo(name = "yieldToMaturity")
    val yieldToMaturity: Double
)
