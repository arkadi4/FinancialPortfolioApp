package com.example.financialportfolioapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financialportfolioapp.data.entities.AssetEntity
import com.example.financialportfolioapp.data.entities.BondEntity
import com.example.financialportfolioapp.data.entities.CashEntity
import com.example.financialportfolioapp.data.entities.PortfolioItemEntity
import com.example.financialportfolioapp.data.entities.StockEntity

@Database(
    entities =
    [
        AssetEntity::class,
        StockEntity::class,
        BondEntity::class,
        CashEntity::class,
        PortfolioItemEntity::class
    ],

    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
    abstract fun portfolioItemDao(): PortfolioItemDao
}
