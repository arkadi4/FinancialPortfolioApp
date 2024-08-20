package com.example.financialportfolioapp.di

import android.content.Context
import androidx.room.Room
import com.example.financialportfolioapp.data.local.AppDatabase
import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.data.local.dao.BondDao
import com.example.financialportfolioapp.data.local.dao.CashDao
import com.example.financialportfolioapp.data.local.dao.StockDao
import com.example.financialportfolioapp.data.local.dao.UniqueIdDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "assets_database.db"
        )
            .createFromAsset("assets_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAssetDao(db: AppDatabase): AssetDao {
        return db.assetDao()
    }
    @Provides
    fun provideStockDao(db: AppDatabase): StockDao {
        return db.stockDao()
    }

    @Provides
    fun provideBondDao(db: AppDatabase): BondDao {
        return db.bondDao()
    }

    @Provides
    fun provideCashDao(db: AppDatabase): CashDao {
        return db.cashDao()
    }

    @Provides
    @Singleton
    fun provideUniqueIdDao(database: AppDatabase): UniqueIdDao {
        return database.uniqueIdDao()
    }
}
