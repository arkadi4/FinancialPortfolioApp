package com.example.financialportfolioapp.di

import com.example.financialportfolioapp.domain.interactor.AssetListInteractor
import com.example.financialportfolioapp.domain.repository.AssetRepository
import com.example.financialportfolioapp.domain.repository.PortfolioItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {
    @Provides
    @Singleton
    fun provideInteractor(
        assetRepository: AssetRepository,
        portfolioItemRepository: PortfolioItemRepository
    ): AssetListInteractor {
        return AssetListInteractor(assetRepository, portfolioItemRepository)
    }
}
