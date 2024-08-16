package com.example.financialportfolioapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "stocks",
    foreignKeys = [
        ForeignKey(
            entity = PortfolioItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["portfolioItemId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StockEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "dividends")
    val dividends: Double,
    @ColumnInfo(name = "portfolioItemId")
    val portfolioItemId: Int
)
