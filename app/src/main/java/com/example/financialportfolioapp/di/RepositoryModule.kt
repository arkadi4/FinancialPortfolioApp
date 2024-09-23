package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.data.local.dao.AssetDao
import com.example.financialportfolioapp.data.local.dao.BondDao
import com.example.financialportfolioapp.data.local.dao.CashDao
import com.example.financialportfolioapp.data.local.dao.StockDao
import com.example.financialportfolioapp.data.local.dao.UniqueIdDao
import com.example.financialportfolioapp.data.repository.AssetRepositoryImpl
import com.example.financialportfolioapp.data.repository.PortfolioItemRepositoryImpl
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAssetRepository(
        assetDao: AssetDao,
        stockDao: StockDao,
        bondDao: BondDao,
        cashDao: CashDao
    ): AssetRepository {
        return AssetRepositoryImpl(assetDao, cashDao, stockDao, bondDao)
    }

    @Provides
    @Singleton
    fun providePortfolioItemRepository(
        assetDao: AssetDao,
        stockDao: StockDao,
        bondDao: BondDao,
        cashDao: CashDao,
        uniqueIdDao: UniqueIdDao
    ): PortfolioItemRepository {
        return PortfolioItemRepositoryImpl(assetDao, stockDao, bondDao, cashDao, uniqueIdDao)
    }
}
