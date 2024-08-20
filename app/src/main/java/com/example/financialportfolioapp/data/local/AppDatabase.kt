package com.example.financialportfolioapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financialportfolioapp.data.entities.AssetEntity
import com.example.financialportfolioapp.data.entities.BondEntity
import com.example.financialportfolioapp.data.entities.CashEntity
import com.example.financialportfolioapp.data.entities.StockEntity
import com.example.financialportfolioapp.data.entities.UniqueIdEntity
import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.data.local.dao.BondDao
import com.example.financialportfolioapp.data.local.dao.CashDao
import com.example.financialportfolioapp.data.local.dao.StockDao
import com.example.financialportfolioapp.data.local.dao.UniqueIdDao

@Database(
    version = 2,
    entities =
    [
        AssetEntity::class,
        StockEntity::class,
        BondEntity::class,
        CashEntity::class,
        UniqueIdEntity::class

    ]
)
@TypeConverters(PortfolioItemConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
    abstract fun bondDao(): BondDao
    abstract fun cashDao(): CashDao
    abstract fun stockDao(): StockDao
    abstract fun uniqueIdDao(): UniqueIdDao
}
