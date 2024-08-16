package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.data.local.AssetDao
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
    fun provideAssetRepository(assetDao: AssetDao): AssetRepository {
        return AssetRepositoryImpl(assetDao)
    }

    @Provides
    @Singleton
    fun providePortfolioItemRepository(): PortfolioItemRepository {
        return PortfolioItemRepositoryImpl()
    }
}
